function searchTopic(object) {
	$.ajax({
		mimeType : "text/html; charset=UTF-8",
		url : 'topic.html',
		data : ({
			name : object
		}),
		success : function(result) {
			$("#same" + object).html(buildTopicReference(result));
		}
	});
}

function buildTopicReference(o) {
	var parsed = JSON.parse(o);
	var res = "";

	for ( var i in parsed) {
		var ref = "#entry" + parsed[i].id;
		res += "<p> <a href=" + ref + ">" + parsed[i].title + "</a></p> \n ";
	}
	return res;
}

window.addEventListener("hashchange", function() {
	scrollBy(0, -60)
})

function refresh() {
	location.reload();
};
