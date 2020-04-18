package com.example.xinychan.common.anno

// 标注注解的注解；Poko 限定于编译期间，以及限定在class范围
// 主要用于 data class 注解
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class Poko