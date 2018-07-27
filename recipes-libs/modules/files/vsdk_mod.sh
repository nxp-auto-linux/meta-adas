#!/bin/sh
### BEGIN INIT INFO
# Provides:          VSDK module insertion in specific order
# Required-Start:    $remote_fs $syslog
# Required-Stop:     $remote_fs $syslog
# Default-Start:     3 5
# Default-Stop:
# Short-Description: Loading VSDK modules
# Description:       Inserts VSDK kernel modules.
### END INIT INFO
# Copyright 2018 NXP


insmod /lib/modules/`uname -r`/apex.ko
insmod /lib/modules/`uname -r`/oal_cma.ko
insmod /lib/modules/`uname -r`/csi.ko
insmod /lib/modules/`uname -r`/cam.ko
insmod /lib/modules/`uname -r`/seq.ko
insmod /lib/modules/`uname -r`/fdma.ko
insmod /lib/modules/`uname -r`/h264enc.ko
insmod /lib/modules/`uname -r`/h264dcd.ko
insmod /lib/modules/`uname -r`/jpegdcd.ko
insmod /lib/modules/`uname -r`/viulite.ko
