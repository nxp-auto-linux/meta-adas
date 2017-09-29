
LOCAL_MIRROR_PATH = "${@d.getVar('FSL_LOCAL_MIRROR', 1).replace('file://','')}"
		
VSDK_LAYOUT_PATH := "${LOCAL_MIRROR_PATH}/s32v234_sdk"
THRDPARTY_LIBS_DIR := "${VSDK_LAYOUT_PATH}/3rdparty"

OUTPUT_DIR_NAME = "ffmpeg/linux-arm64"
INSTALL_DIR = "${THRDPARTY_LIBS_DIR}/${OUTPUT_DIR_NAME}"


do_install_append() {
  mkdir -p "${INSTALL_DIR}"
  cp -r ${D}/usr/lib/ "${INSTALL_DIR}/"
  cp -r ${D}/usr/bin/ "${INSTALL_DIR}/"
  cp -r ${D}/usr/include/ "${INSTALL_DIR}/"
  cp -r ${D}/usr/share/ "${INSTALL_DIR}/"
}

