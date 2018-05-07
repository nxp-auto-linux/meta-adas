# This recipe deploy the M0 toolchain in binary format required by VSDK

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit native

M0_PACKAGE = "6.3.1-2017.05"

SRC_URI = "https://releases.linaro.org/components/toolchain/binaries/6.3-2017.05/arm-eabi/gcc-linaro-${M0_PACKAGE}-x86_64_arm-eabi.tar.xz"
SRC_URI[md5sum] = "3e768e03923519e7c7064317aebef491"
SRC_URI[sha256sum] = "499aee1c11b4d90551ff55476cab3edb143cdc325f0c3cf852adb7488307e953"

do_install_append() {
	ln -sf ${WORKDIR}/gcc-linaro-${M0_PACKAGE}-x86_64_arm-eabi ${M0_TOOLCHAIN_DIR}
}
