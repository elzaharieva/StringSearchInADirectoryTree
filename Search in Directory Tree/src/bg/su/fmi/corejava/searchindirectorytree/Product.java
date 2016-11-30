package bg.su.fmi.corejava.searchindirectorytree;

public class Product {
	private String fileName;
	private Integer lineNum;
	private String line;
	private boolean found;

	public Product(String fileName, Integer lineNum, String line) {
		this.fileName = fileName;
		this.lineNum = lineNum;
		this.line = line;
	}

	public Product() {
		this.fileName = "";
		this.lineNum = -1;
		this.line = "";
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	public boolean isFound() {
		return found;
	}

	@Override
	public String toString() {
		return "FileName: '" + this.fileName + "', Line Number: '" + this.lineNum + "', Line: '" + this.line + "'";
	}
}
