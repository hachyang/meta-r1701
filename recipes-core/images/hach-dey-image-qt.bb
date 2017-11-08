SUMMARY = "A QT5 image derived from dey-image-qt"
LICENSE = "MIT"

# dey-image-qt.bb is a good starting point for our custom r1701 image. For example, it includes the packagegroup
#   dey-bluetooth: adds “meta-digi-dey/recipes-connectivity/packagegroups/packagegroup-dey-bluetooth.bb”
#   dey-qt: adds “meta-digi-dey/recipes-graphics/packagegroups/packagegroup-dey-qt.bb”
#   etc.
require recipes-core/images/dey-image-qt.bb

inherit populate_sdk_qt5

R1701_INSTALL = " \
    rsync \
    tmux \
    devmem2 \
    htop \
    zeromq \
    boost \
    protobuf \
    lighttpd \
    fcgi \
    sqlite3 \
"

# Custom kernel modules built out of tree
KERNEL_MODULES_OOT = "\
    hach-kernel-modules \
"

# Add predefined runtime package groups. 
IMAGE_FEATURES += " \
    eclipse-debug \
    qtcreator-debug \
    ssh-server-openssh \
    tools-debug \
"

# Add individual recipes.
IMAGE_INSTALL_append = " \
    ${R1701_INSTALL} \
    ${KERNEL_MODULES_OOT} \
"

# Add a new filesystem
IMAGE_FSTYPES_append = " sdcard"

# Assign a password to root account.
inherit extrausers
ROOT_PASSWORD = "hqd123"
EXTRA_USERS_PARAMS = " \
    usermod -p 'openssl passwd ${ROOT_PASSWORD}' root; \
"

DISTRO_FEATURES_remove = "x11"

export IMAGE_BASENAME = "hach-image"
