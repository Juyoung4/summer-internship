
	var NUMBERS = "0123456789.";

	function numberonly(value){
		if(value==undefined){
			return "";
		}
		var retval = "";
		var dot = false;
		var len = value.length;
		for(var index=0; index<len; index++){
			var ch = value.charAt(index);
			if(NUMBERS.indexOf(ch)<0){
			}else{
				if(ch=='.'){
					if(dot){
					}else{
						dot = true;
						retval += ch;
					}
				}else{
					retval += ch;
				}
			}
		}
		return retval;
	}
