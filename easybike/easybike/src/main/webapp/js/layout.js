var tabCache = new Object;

$(function () {
    $('#tabs').tabs({
        onSelect: function (title, index) {
            tabCache[title] = index;
        },
        onClose: function (title, index) {
            delete tabCache[title];
        }
    });
});

function addNewTab(title, url) {
	var content='<iframe width="100%" height="99%" frameborder="0" marginwidth="5" marginheight="5" ' +
    ' allowtransparency="yes" src="' + url + '" ></iframe>';
    if (tabCache[title] == undefined) {
        if (url == '') return;
        $('#tabs').tabs('add', {
            title: title,
            content:content,
            closable:true
        });
    } else {    	
        $('#tabs').tabs('select', parseInt(tabCache[title]));
        var tab = $('#tabs').tabs('getSelected');  // get selected panel
        $('#tabs').tabs('update', {
        	tab: tab,
        	options: {
        		content:content                
        	}
        });
    }
}
