package org.ravenbuild.plugins.ides.intellij.xml.module;

import com.ravensuite.ravenxml.Element;
import com.ravensuite.ravenxml.TagName;
import org.ravenbuild.plugins.ides.intellij.xml.ModuleContent;

public class ExcludeOutput extends Element implements ModuleContent {
	public ExcludeOutput() {
		super(new TagName("exclude-output"));
	}
}
