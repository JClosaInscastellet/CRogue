
# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.22.1)
set(sfml_dir ${CMAKE_SOURCE_DIR}/../../../../sfml)
set(sfml_lib /home/judit/AndroidStudioProjects/CRogue/SFML/lib/${ANDROID_ABI})
set(sfml_include ${sfml_dir}/include)
include_directories(
        ${sfml_include}
)
#--- ADD LIBS: prebuilt libraries (3rd party)
add_library(sfml-main STATIC IMPORTED)
set_target_properties(sfml-main PROPERTIES IMPORTED_LOCATION ${sfml_lib}/libsfml-main.a)

add_library(sfml-activity SHARED IMPORTED)
set_target_properties(sfml-activity PROPERTIES IMPORTED_LOCATION ${sfml_lib}/libsfml-activity.so)

add_library(sfml-system SHARED IMPORTED)
set_target_properties(sfml-system PROPERTIES IMPORTED_LOCATION ${sfml_lib}/libsfml-system.so)

add_library(sfml-window SHARED IMPORTED)
set_target_properties(sfml-window PROPERTIES IMPORTED_LOCATION ${sfml_lib}/libsfml-window.so)

add_library(sfml-graphics SHARED IMPORTED)
set_target_properties(sfml-graphics PROPERTIES IMPORTED_LOCATION ${sfml_lib}/libsfml-graphics.so)

add_library(sfml-audio SHARED IMPORTED)
set_target_properties(sfml-audio PROPERTIES IMPORTED_LOCATION ${sfml_lib}/libsfml-audio.so)

add_library(openal SHARED IMPORTED)
set_target_properties(openal PROPERTIES IMPORTED_LOCATION ${sfml_lib}/libopenal.so)
set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -u ANativeActivity_onCreate")


# Declares and names the project.

project("crogue")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        crogue

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        crogue.cpp )
target_include_directories(crogue PRIVATE
        ${sfml_include}
        )
# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        crogue
        android
        log

        EGL
        GLESv1_CM

        openal
        sfml-audio

        sfml-system
        sfml-window
        sfml-graphics
        sfml-activity


        # Links the target library to the log library
        # included in the NDK.
        ${log-lib} )
