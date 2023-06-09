package com.example.testapp.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testapp.data.Product
import com.example.testapp.ui.theme.TestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GetData()
                }
            }
        }
    }
}

@Composable
fun GetData(viewModel: MainViewModel = hiltViewModel()) {
    val state = viewModel.dummyData.collectAsState()
    when (state.value) {
        is MainViewModel.Event.Loading -> {

        }

        is MainViewModel.Event.Success -> {
            CreateDummyList(data = (state.value as MainViewModel.Event.Success).resultData?.products)
        }

        is MainViewModel.Event.Failure -> {

        }
        else -> {}
    }
}

@Composable
fun CreateDummyList(data: List<Product>?) {
    data?.let { list ->
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
            items(
                list,
                itemContent = {
                    ProductListItem(product = it)
                }
            )
        }
    }
}