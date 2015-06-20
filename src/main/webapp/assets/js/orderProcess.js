var sum = 0;
var itemcount = 0;
var totalcount=0;
var itemName='';

function addTolist(Product) {
	var pname = $(Product).attr('pname');
	itemName=pname;
	var pprice = $(Product).attr('pprice');
	var pmid=$(Product).attr('pmid');
	var pqty = $('#quantity_'+pmid).val();
	
	var countexist=checkExistance(pmid);
	pqty=parseInt(pqty)+parseInt(countexist);
	
	var amount = pprice * pqty;
	sum = sum + amount;
	itemcount += 1;
	totalcount += 1;

	html =  "<tr id='" + pmid + "'>" +
			"<td attr-tr='"+pmid+"' id='" + pmid + "_order_name'>" + pname+ "</td> " +
			"<td id='" + pmid + "_order_price'>" + pprice + " </td> " +
			"<td id='" + pmid + "_order_qty'>" +
			"<span id='" + pmid + "_qty'>" + pqty + "</span>" +
			"<input style='width:60px;display:none;' type='number' attr-id='" + pmid + "' id='" + pmid + "_qtyedit' value='" + pqty + "' min='1'/>" +
			"</td>" + 
			"<td id='" + pmid + "_order_amnt'>" + amount + " </td>" +
			"<td>"+ 
			"<input attr-price='"+amount+"' type='button' style='margin-left:0px;padding:2px;width:48%' class='btn btn-warning'  id='" + pmid + "_editItem' onclick='editItem(" + pmid + ");' value='Edit'/>" +
			"<input type='button' style='margin-left:0px;padding:2px;display:none;width:48%' class='btn btn-success' pname='" + pname+ "' pprice='" + pprice + "' pmid='" + pmid + "'  id='" + pmid + "_saveItem' onclick='saveItem(" + pmid + ");' value='OK' />" +
			"<input attr-pmid='"+pmid+"' type='button' style='margin-left:2px;padding:2px;width:48%' class='btn btn-danger'  id='" + pmid + "_removeItem' onclick='removeme(this);' value='Del'/>" +
			"</td> " +
			"</tr>";
	$("#bill_table").append(html);
	
	handleCount(totalcount);
	displaysum();
}

function makeEdit(id) {
	var qty=$('#'+id+'_qtyedit').val();
	var bqty=parseInt($('#'+id+'_qty').text());
	var price=parseInt($('#'+id+'_order_price').text());
	var amt=qty*price;
	$('#'+id+'_qty').text(qty);
	$('#'+id+'_order_amnt').text(amt);
	sum=sum-bqty*price;
	sum=sum+amt;
	displaysum();
}

function editItem(id) {
	$('#'+id+'_saveItem').show();
	$('#'+id+'_editItem').hide();
	
	$('#'+id+'_qtyedit').show();
	$('#'+id+'_qty').hide();
}

function saveItem(id) {
	$('#'+id+'_saveItem').hide();
	$('#'+id+'_editItem').show();
	
	$('#'+id+'_qtyedit').hide();
	$('#'+id+'_qty').show();
	
	makeEdit(id);
}

function checkExistance(id) {
	var qty=0;
	$('#bill_table').find('tr').each(function() {
		var firstTD = $(this).find('td:first');
		if(firstTD.attr('attr-tr')===id){
			qty=$('#'+id+'_order_qty').text().trim();
			$('#'+id+'_removeItem').click();
		}
	});

	return qty;
}

function displaysum() {
	$("#totalSumOfAmount").html('Total amount is: '+sum);
}

function removeme(that) {
	var id=that.getAttribute('attr-pmid');
	var bqty=parseInt($('#'+id+'_qty').text());
	var price=parseInt($('#'+id+'_order_price').text());
	var amt=bqty*price;
	sum=sum-amt;

	$(that).parent().parent().remove();
	
	totalcount -= 1;
	handleCount(totalcount);
	displaysum();
}

function handleCount(count) {
	$('#orderCounts').hide(100);	
	if(totalcount!=0){
		$('#orderCounts').show(300);
		$('#orderMessage').html('<b>'+itemName+'</b> added');
		$('#reviewOrder').show(100);
	}else{
		$('#bottomCartReview').slideUp(300);
		$('#reviewOrder').hide(100);
		$('#orderMessage').html("You haven't ordered anything yet.");
	}
	$('#orderCounts').html(totalcount);
}

$('#reviewOrder').click(function() {
	$('#bottomCartReview').slideDown(500);
});

function sumOfColumns(tableID, columnIndex, hasHeader) {
	var tot = 0;
	$("#" + tableID + " tr" + (hasHeader ? ":gt(0)" : "")).children(
			"td:nth-child(" + columnIndex + ")").each(function() {
		tot += parseInt($(this).html());
	});
	return tot;
}

$('.hideReview').click(function() {
	var target=$(this).attr('attr-target');
	$('#'+target).slideUp(300);
});

$('#qaz').click(function() {
					var menuId = '';
					var orderDetails = [];
					var temp = '';
					var data = new Object();
					data.tableId = $('#tableId').val();
					$('#bill_table > tbody  > tr')
							.each(
									function(i, el) {
										if (i > 0) {
											menuId += $(this).attr('id') + ',';
											var $tds = $(this).find('td'), productId = $tds
													.eq(0).text(), product = $tds
													.eq(1).text(), Quantity = $tds
													.eq(2).text();
											item = {};
											item['id'] = $(this).attr('id');
											item['quantity'] = Quantity;

											orderDetails.push(item);
										}
									});
					data.menuId = menuId.slice(0, -1);
					data.orderDetails = orderDetails;
					console.log(data);
					$.ajax({
						type : 'post',
						beforeSend: function (request)
			            {
			                request.setRequestHeader("SID", getSID());
			            },
						url : window.glob+'/restaurant/placeOrder',
						data : JSON.stringify(data),
						contentType : "application/json; charset=utf-8",
						traditional : true,
						success : function(data) {
							if ('SUCCESS' == data){
								swal("Order Confirmed!",
										"Your order will be served in 15-20 min!",
										"success");
								
								$('.confirm').click(function() {
									j_loader_GET(window.glob+"/restaurant/menu", "html", "mainContainer");
								});
							}
							else{
								sweetAlert("Oops...", "Something went wrong!",
										"error");
							}
						}
					});
});