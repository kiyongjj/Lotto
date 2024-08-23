// 랙을 보기 위한 카운터
let counter = 0;

$(function() {
	// 매 초마다 카운터 갱신
	setInterval(function() {
		counter++;
		$('#counter').html(counter);
	}, 1000);
  
	// 데이터 생성
	let data = [];
	for (let i = 0; i < 100000; i++) {
		data.push('아이템' + i);
	}
	
	// 클릭 이벤트 핸들러 
	$('#title').click(function(e) {
		$('#popup').toggle();
	});

	console.time();

	$("#list").click(function(e) { 
		let $this = $(e.target);
		$("#title").text($this.text());
		$("#popup").hide();
	});

	// 데이터로 셀렉트박스 항목 만들기
	let str = "";
	for (let i = 0; i < data.length; i++) { 
		let elem = createItem(data[i]);
		str += elem;
	}
	$("#list").html(str);
	console.timeEnd();
});

function createItem(d) {
	let elem = '<li class="item">' + d + '</li>';
	return elem;
}
