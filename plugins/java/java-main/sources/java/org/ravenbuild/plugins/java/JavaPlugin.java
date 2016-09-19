package org.ravenbuild.plugins.java;

import org.ravenbuild.plugins.BuildPlugin;
import org.ravenbuild.plugins.PluginContext;
import org.ravenbuild.plugins.build.BuildProjectPlugin;
import org.ravenbuild.plugins.java.dependencies.JavaDependenciesPlugin;
import org.ravenbuild.plugins.java.projectstructure.JavaProjectStructure;
import org.ravenbuild.plugins.java.projectstructure.SourceFolder;

public class JavaPlugin implements BuildPlugin {
	private final JavaProjectStructure javaProjectStructure = new JavaProjectStructure();
	
	private JavaDependenciesPlugin javaDependenciesPlugin;
	private BuildProjectPlugin buildProjectPlugin;
	
	@Override
	public void initialize(final PluginContext pluginContext) {
		//FIXME maybe a compiler plugin should actually add the source folders?
		javaProjectStructure.addSourceFolder(new SourceFolder("sources/java", SourceFolder.Scope.PRODUCTION));
		javaProjectStructure.addSourceFolder(new SourceFolder("tests/java", SourceFolder.Scope.DEVELOPMENT));
		
		javaDependenciesPlugin = pluginContext.dependsOnPlugin(JavaDependenciesPlugin.class);
		buildProjectPlugin = pluginContext.dependsOnPlugin(BuildProjectPlugin.class);
		
		javaDependenciesPlugin.reportDependenciesTo(javaProjectStructure);
	}
	
	@Override
	public String getId() {
		return "org.ravenbuild.java";
	}
}
