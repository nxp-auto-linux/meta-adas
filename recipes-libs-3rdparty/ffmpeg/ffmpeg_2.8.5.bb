LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

require recipes-libs-3rdparty/common-3rdparty.inc
inherit insane


LIBDIR_NAME = "ffmpeg"
EXTERNALSRC_BUILD = "${THRDPARTY_LIBS_DIR}/${LIBDIR_NAME}/linux-arm64"

# TODO: build from source
# * there is a .sh script provided in the vsdk demos dir, unfortunately
#   the compiler is hardcoded

# SRC_URI = "https://www.ffmpeg.org/releases/ffmpeg-2.8.5.tar.bz2"
# do_configure() {
# 	./configure \
# 		--enable-cross-compile                                              \
# 		--enable-shared                                                     \
# 		--disable-static                                                    \
# 		--cross-prefix=${CROSS_COMPILE}                                     \
# 		--arch=aarch64                                                      \
# 		--target-os=linux                                                   \
# 		--sysroot=${STAGING_DIR_TARGET}  									\
# 		--prefix=${INSTALL_DIR}
# }
#
# do_compile() {
# 	oe_runmake 
# }
			


do_install_append() {
  cp lib/libavcodec.so	"${INSTALL_DIR}/libavcodec.so.56"
  cp lib/libavdevice.so 	"${INSTALL_DIR}/libavdevice.so.56"
  cp lib/libavfilter.so	"${INSTALL_DIR}/libavfilter.so.5"
  cp lib/libavformat.so 	"${INSTALL_DIR}/libavformat.so.56"
  cp lib/libavutil.so   	"${INSTALL_DIR}/libavutil.so.54"
  cp lib/libswresample.so "${INSTALL_DIR}/libswresample.so.1"
  cp lib/libswscale.so 	"${INSTALL_DIR}/libswscale.so.3"

  cp -r bin/ ${D}/${OUTPUT_DIR_NAME}/
  cp -r include/ ${D}/${OUTPUT_DIR_NAME}/
  cp -r share/ ${D}/${OUTPUT_DIR_NAME}/

}

INSANE_SKIP_${PN} += "ldflags"
INSANE_SKIP_${PN} += "already-stripped"
