DESCRIPTION = "Vision SDK demo applications"
#HOMEPAGE = ""
#SECTION = ""
#DEPENDS = ""

inherit externalsrc

# We need to use a basic filepath here
SOURCES_PATH = "${@d.getVar('FSL_LOCAL_MIRROR', 1).replace('file://','')}"

EXTERNALSRC := "${SOURCES_PATH}/s32v234_sdk"

THRDPARTY_LIBS_DIR := "${EXTERNALSRC}/3rdparty"

OUTPUT_DIR_NAME = "usr"
INSTALL_DIR = "${D}/${OUTPUT_DIR_NAME}/lib"

# Don't compile anything by default. Most 3rd party libs are delivered prebuilt.
do_compile() {
	:
}

do_install() {
	mkdir -p "${INSTALL_DIR}"
}

FILES_${PN} += "/${OUTPUT_DIR_NAME}"
FILES_${PN}-dbg += "/${OUTPUT_DIR_NAME}/.debug"
