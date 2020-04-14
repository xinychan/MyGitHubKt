package com.example.xinychan.common.anno

// 标注注解的注解；Poko 限定于编译期间，以及限定在class范围
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class Poko