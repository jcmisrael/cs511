cmake_minimum_required (VERSION 3.1)
project (GymSimulator)

# Version Number
set (GYM_VERSION_MAJOR 0)
set (GYM_VERSION_MINOR 1)

set(BINARY_NAME
  GymSimulator)

set (PACKAGE
  "src/edu/stevens/jisrael/assignment2"
  )

FUNCTION(PREPEND var prefix)
  SET(listVar "")
  FOREACH(f ${ARGN})
    LIST(APPEND listVar "${prefix}/${f}")
  ENDFOREACH(f)
  SET(${var} "${listVar}" PARENT_SCOPE)
ENDFUNCTION(PREPEND)

# Enable testing
enable_testing()

# Require Java
find_package(Java REQUIRED)
include(UseJava)

# Add source files
set (SOURCE_FILES
  ${SOURCE_FILES}
  HelloThreadRunnable.java
  )

PREPEND(SOURCE ${PACKAGE} ${SOURCE_FILES})

# Construct normal executable
add_jar(${BINARY_NAME}
  ${SOURCE}
  ENTRY_POINT edu/stevens/jisrael/assignment2/HelloThreadRunnable
  )
