lunifera-osgi-web
=================

OSGi bundles for lunifera web integration



This repo contains lunifera components that are related with web stuff.

How to install:
* Clone the repo
* Download vaadin patch vaadin-patch.zip (until vaadin7 beta3 is released, the patch has to be used) from https://github.com/florianpirchner/lunifera-osgi-web/downloads
* Unzip the content into {yourWorkspace}/lunifera-osgi-web/org.lunifera.web.p2.target.juno/local (afterwards local folder p2)
* Refresh all projects in eclipse
* Run maven build (clean verify) on lunifera-osgi-web/pom.xml
* After run, refresh project org.lunifera.web.p2.repo.juno and copy org.apache.shiro.core_1.2.1.jar, org.apache.shiro.web_1.2.1.jar and org.jsoup_1.6.3.jar to org.lunifera.web.p2.target.juno/local
* Open org.lunifera.web.p2.target.juno/lunifera-juno.target, wait until the target definition is resolved and then press "set as target"
* Now the workspace should compile properly