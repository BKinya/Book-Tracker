package com.beatrice.bookapp.catalogue.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.beatrice.bookapp.catalogue.domain.model.Book
import com.beatrice.bookapp.catalogue.ui.adapter.BookAdapter
import com.beatrice.bookapp.core.util.Result
import com.beatrice.bookapp.databinding.FragmentCatalogueBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class CatalogueFragment : Fragment() {
    private var _binding: FragmentCatalogueBinding? = null
    private val binding get() = _binding!!
    private val catalogueViewModel: CatalogueViewModel by viewModel()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var booksAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogueBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.booksRecyclerview.layoutManager = linearLayoutManager
        collectBooks()
        observeMessage()
        observeError()
        saveBooks()
    }

    private fun collectBooks() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                catalogueViewModel.books.collect { result ->
                    when (result) {
                        is Result.Success -> {
                            val books = result.data
                            if (books.isNotEmpty()) {
                                booksAdapter = BookAdapter(books)
                                binding.booksRecyclerview.adapter = booksAdapter
                            }
                        }
                        is Result.Error -> {
                            showSnackBar(result.error!!)
                        }
                    }
                }
            }
        }
    }

    private fun observeMessage() {
        catalogueViewModel.message.observe(viewLifecycleOwner, { message ->
            showSnackBar(message)
        })
    }

    private fun observeError() {
        catalogueViewModel.error.observe(viewLifecycleOwner, { error ->
            showSnackBar(error)
        })
    }

    private fun saveBooks() {
        val books = generateBooks()
        binding.addBookFab.setOnClickListener {
            val randomIndex = Random.nextInt(0, books.size)
            val bookToSave = listOf(
                books[randomIndex]
            )
            catalogueViewModel.saveBooks(bookToSave)
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun generateBooks() = listOf(
        Book(
            id = 0,
            title = "... Dance of the Jakaranda",
            author = listOf("Peter kimani"),
            genre = "Historical Fiction",
            hasRead = true
        ),
        Book(
            id = 2,
            title = "INFJ 101",
            author = listOf("Lindsay Rossum"),
            genre = null,
            hasRead = true
        ),
        Book(
            id = 3,
            title = "Year One",
            author = listOf("Nora Roberts"),
            genre = null,
            hasRead = false
        ),
        Book(
            id = 5,
            title = "The Rise Of Magics",
            author = listOf("Nora Roberst"),
            genre = null,
            hasRead = true
        ),
        Book(
            id = 6,
            title = "Of Blood And Bone",
            author = listOf("Nora Roberts"),
            genre = null,
            hasRead = true
        )
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
