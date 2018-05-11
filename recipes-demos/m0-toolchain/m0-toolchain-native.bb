# This recipe deploy the M0 toolchain in binary format required by VSDK

LICENSE = "GPL-3.0-with-GCC-exception & GPLv3"
LIC_FILES_CHKSUM = "\
    file://${COMMON_LICENSE_DIR}/GPL-3.0-with-GCC-exception;md5=aef5f35c9272f508be848cd99e0151df \
    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
"

inherit native

M0_PACKAGE = "6.3.1-2017.05"

SRC_URI = "https://releases.linaro.org/components/toolchain/binaries/6.3-2017.05/arm-eabi/gcc-linaro-${M0_PACKAGE}-x86_64_arm-eabi.tar.xz"
SRC_URI[md5sum] = "3e768e03923519e7c7064317aebef491"
SRC_URI[sha256sum] = "499aee1c11b4d90551ff55476cab3edb143cdc325f0c3cf852adb7488307e953"

do_install_append() {
	ln -sf ${WORKDIR}/gcc-linaro-${M0_PACKAGE}-x86_64_arm-eabi ${M0_TOOLCHAIN_DIR}
}
