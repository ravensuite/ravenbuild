package org.ravenbuild.projectinfo;

import java.io.File;

public class ProjectInfoLoader {
	private final ProjectInfo projectInfo = new ProjectInfo();
	
	public ProjectInfoLoader() {
		String defaultName = new File("").getAbsoluteFile().getName();
		
		projectInfo.setProjectName(defaultName);
		projectInfo.setProjectGroup(defaultName);
		projectInfo.setProjectVersion("UNKNOWN_VERSION");
	}
	
	public ProjectInfoLoader(final ProjectInfo parent) {
		this();
		projectInfo.setProjectGroup(parent.getProjectGroup());
	}
	
	public ProjectInfo projectInfo() {
		return projectInfo;
	}
}