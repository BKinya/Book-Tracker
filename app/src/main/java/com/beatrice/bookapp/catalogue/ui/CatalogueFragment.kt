package com.beatrice.bookapp.catalogue.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.beatrice.bookapp.catalogue.domain.model.Book
import com.beatrice.bookapp.catalogue.ui.adapter.BookAdapter
import com.beatrice.bookapp.databinding.FragmentCatalogueBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.random.Random

class CatalogueFragment : Fragment() {
    private var _binding: FragmentCatalogueBinding? = null
    private val binding get() = _binding!!
    private val catalogueViewModel: CatalogueViewModel by viewModel()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var booksAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatalogueBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.booksRecyclerview.layoutManager = linearLayoutManager
        observeBooks()
        observeMessage()
        observeError()
        saveBooks()
    }

  private fun observeBooks() {
        catalogueViewModel.books.observe(viewLifecycleOwner, { books ->
            if (books.isNotEmpty()) {
                booksAdapter = BookAdapter(books)
                binding.booksRecyclerview.adapter = booksAdapter

            } else {
                showSnackBar("No books yet")
            }
        })
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
            val randomIndex = Random.nextInt(0,books.size)
            val bookToSave = listOf(
                books[randomIndex]
            )
            catalogueViewModel.saveBooks(bookToSave)
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
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
            title = "The Big Picture",
            author = listOf("Ben Carson"),
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
            title = "Rich Dad Poor Dad",
            author = listOf("Robert Kiyosaki"),
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