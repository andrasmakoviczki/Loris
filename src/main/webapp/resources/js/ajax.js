function searchTopic(object) {
	
	$.ajax({
		mimeType : "text/html; charset=UTF-8",
		url : 'topic.html',
		data : ({
			name : object
		}),
        success:function(result){
            $("#same" + object).html(buildTopicReference(result));
        }
    });
}

function buildTopicReference(o) {
	var parsed = JSON.parse(o);
	var res = "";
	
	for (var i in parsed) {
		var ref = "#entry" + parsed[i].id;
		res += "<p> <a href=" + ref + ">" + parsed[i].title + "</a></p> \n ";
	}
	return res;
}

function alma(object) {
    alert(searchTopic(object))
};

function refresh() {
    location.reload();
};
