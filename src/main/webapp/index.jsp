<!Doctype>
<html lang="en">
	<head>
		<title>XY Inc.</title>
		<script type="text/javascript" src="resources/js/jquery-2.1.1.min.js"></script>
	</head>
	<body>
		<h2>XY Inc.</h2>
		
		<div>
			<h5>Create new POI</h5>
			<p>
				<label>Name: </label>
				<input id="name" type="text">
			</p>
			<p>
				<label>X Coordinate: </label>
				<input id="x" type="text">
			</p>
			<p>
				<label>Y Coordinate: </label>
				<input id="y" type="text">
			</p>
			<p>
				<input type="button" value="Create" id="createButton">
			</p>
		</div>
		<script type="text/javascript">
			$(function() {
				POI.init();
			});
			
			var POI = new function() {
				
				this.init = function() {
					nameField = $("#name");
					xField = $("#x");
					yField = $("#y");
					createButton = $("#createButton");
					
					createButton.click(function() {
						$.post("/xy-inc/rest/pois/create", { name : nameField.val(), x : xField.val(), y : yField.val() });
						nameField.val('');
						xField.val('');
						yField.val('');
					});
				};
			};
		</script>
	</body>
</html>
