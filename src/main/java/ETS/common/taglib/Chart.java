package ETS.common.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("serial")
public class Chart extends BodyTagSupport {

	PageContext pageContext;

	private String 		id;
	private String 		type;
	private String 		data;
	private boolean 	legend = true;
	private int 		width = 900;
	private int 		height = 300;
	private boolean 	animation = true;
	private int 		animationSteps = 100;
	private boolean 	scaleShowGridLines = false;
	private boolean 	showTooltips = true;
	private String 		onAnimationProgress = null;
	private String 		onAnimationComplete = null;
	
	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void setLegend(boolean legend) {
		this.legend = legend;
	}
	public void setWidth(int width){
		this.width = width;
	}
	public void setHeight(int height){
		this.height = height;
	}
	public void setAnimation(boolean animation){
		this.animation = animation;
	}
	public void setAnimationSteps(int animationSteps){
		this.animationSteps = animationSteps;
	}
	public void setScaleShowGridLines(boolean scaleShowGridLines){
		this.scaleShowGridLines = scaleShowGridLines;
	}
	public void setShowTooltips(boolean showTooltips){
		this.showTooltips = showTooltips;
	}
	public void setOnAnimationProgress(String onAnimationProgress){
		this.onAnimationProgress = onAnimationProgress;
	}
	public void setOnAnimationComplete(String onAnimationComplete){
		this.onAnimationComplete = onAnimationComplete;
	}
	
	public int doStartTag() throws JspException {
		StringBuffer tag = new StringBuffer();
		
		type = type.toLowerCase();
		type = StringUtils.capitalize(type);
		
		if( !("Bar").equals(type) && !("Line").equals(type) ){
			return 0;
		}
		
		try{
			if( this.legend ){
				tag.append("<div id='" + this.id + "_legend' class='legend' style='position: relative; margin-right:.5em;'>\n");
				tag.append("</div>\n");
			}
			
			tag.append("<canvas id='" + this.id + "' width='" + this.width + "' height='" + this.height + "'></canvas>\n");
			
			tag.append("<script type=\"text/javascript\">\n");
			tag.append("	var data = " + this.data + ";\n");
			tag.append("	var " + this.id + "Chart = jsChart.util.chart('" + this.id + "', '" + this.type + "', data, " + this.legend);
			tag.append("								, " + this.animation + ", '" + this.animationSteps + "', " + this.scaleShowGridLines + ", " + this.showTooltips + ", " + this.onAnimationProgress + ", " + this.onAnimationComplete +");\n");

			tag.append("	function " + this.id + "Img(){return " + this.id + "Chart.toBase64Image()}\n");
			tag.append("	function " + this.id + "Html(){return " + this.id + "Chart.generateLegend()}\n");
			tag.append("	function " + this.id + "Destroy(){return " + this.id + "Chart.destroy()}\n");
			tag.append("</script>\n");
			
			pageContext.getOut().write(tag.toString());
		}catch(Exception e){

		}
		return 0;
	}

	public int doEndTag() {
		return 0;
	}
}
