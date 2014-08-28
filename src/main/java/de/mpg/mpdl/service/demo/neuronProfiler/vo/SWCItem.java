package de.mpg.mpdl.service.demo.neuronProfiler.vo;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SWCItem implements Serializable {

	private static final long serialVersionUID = -3859411517798752904L;

	private File swcFile;
	private File swcRespHTMLFile;
	private String screenshotFilePath;
	private List<SWCMetadata> mds = new ArrayList<SWCMetadata>();
	
	public File getSwcFile() {
		return swcFile;
	}

	public void setSwcFile(File swcFile) {
		this.swcFile = swcFile;
	}

	public File getSwcRespHTMLFile() {
		return swcRespHTMLFile;
	}

	public void setSwcRespHTMLFile(File swcRespHTMLFile) {
		this.swcRespHTMLFile = swcRespHTMLFile;
	}

	public String getScreenshotFilePath() {
		return screenshotFilePath;
	}

	public void setScreenshotFilePath(String screenshotFilePath) {
		this.screenshotFilePath = screenshotFilePath;
	}

	public List<SWCMetadata> getMds() {
		return mds;
	}

	public void setMds(List<SWCMetadata> mds) {
		this.mds = mds;
	}

}
