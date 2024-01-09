package com.gurpreetsk

import com.github.ajalt.clikt.core.CliktCommand

class CanaryCommand : CliktCommand() {

    override fun run() {
        echo("Success!", trailingNewline = false)
    }
}
