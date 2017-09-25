LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://3rdparty/ocv/standalone-arm64/LICENSE;md5=0ea90d28b4de883d7af5e6711f14f7bf"

require recipes-libs-3rdparty/common-3rdparty.inc
inherit insane

EXTERNALSRC_BUILD = "${THRDPARTY_LIBS_DIR}/ocv/linux-arm64/lib"

do_install_append() {
  find . -name "*.so" -exec cp "{}" "${INSTALL_DIR}/{}.${PV}" \;
  cp -r ../include/ ${D}/${OUTPUT_DIR_NAME}/
  cp -r ../share/ ${D}/${OUTPUT_DIR_NAME}/
}

INSANE_SKIP_${PN} += "ldflags"
INSANE_SKIP_${PN} += "build-deps"

FILES_${PN} += "/usr/share/OpenCV/.cmake"
FILES_${PN} += "/usr/share/OpenCV/*.xml"
