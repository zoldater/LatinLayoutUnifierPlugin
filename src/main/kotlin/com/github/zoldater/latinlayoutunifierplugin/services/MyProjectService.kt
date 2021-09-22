package com.github.zoldater.latinlayoutunifierplugin.services

import com.intellij.openapi.project.Project
import com.github.zoldater.latinlayoutunifierplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
