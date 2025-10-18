package com.messenger.core.theme.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SmallProgressIndicator(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier.size(32.dp))
}