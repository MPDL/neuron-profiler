/**
 * 
 */
$(function(e) {
	var obj = $('#top').find('.ui-layout-unit-content');
	obj.css({"background":"#51BEDB"});
//	$('.ui-fileupload-buttonbar').css({"background":"#51BEDB"});
});


function afterUpload()
{
	window.location.reload();
}