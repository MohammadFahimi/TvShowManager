package com.mfahimi.tvshowmanager.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfahimi.domain.formatter.DateFormatter
import com.mfahimi.domain.model.output.TvShowData
import com.mfahimi.tvshowmanager.databinding.FragmentTvShowsBinding
import com.mfahimi.tvshowmanager.ext.gone
import com.mfahimi.tvshowmanager.ext.handleApiResponse
import com.mfahimi.tvshowmanager.ext.visible
import com.mfahimi.tvshowmanager.ui.adapter.MoviesAdapter
import com.mfahimi.tvshowmanager.viewmodel.TvShowViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowsFragment : BaseFragment<FragmentTvShowsBinding>() {
    override val viewBinding: FragmentTvShowsBinding by lazy { FragmentTvShowsBinding.inflate(layoutInflater) }
    private val viewModel: TvShowViewModel by viewModel()
    private val dateFormatter: DateFormatter by inject()
    private val tvShows = ArrayList<TvShowData>()
    private val adapter = MoviesAdapter(tvShows, dateFormatter)
    private var pageIndex = 0
    private var isMoreDataAvailable = false

    override fun initObjects(savedInstanceState: Bundle?) {
        super.initObjects(savedInstanceState)
        viewModel.tvShowsObservable.handleApiResponse(this,
            onLoad = {
                if (pageIndex == 0)
                    binding.progressBar.visible()
            },
            onSuccess = {
                binding.progressBar.gone()
                addNewMovies(it)
                adapter.isLoading = false
            },
            onError = {
                adapter.isLoading = false
                binding.progressBar.gone()
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            })
    }

    override fun initViews(view: View, savedInstanceState: Bundle?) = with(binding) {
        progressBar.visible()
        rvTvShows.layoutManager = LinearLayoutManager(requireContext())
        rvTvShows.adapter = adapter
        adapter.onEndOfListReached = {
            if (isMoreDataAvailable) {
                pageIndex++
                getTvShows()
                adapter.isLoading = true
            }
        }
        getTvShows()
    }

    private fun getTvShows() {
        viewModel.getTvShows(pageIndex)
    }

    private fun addNewMovies(tvShows: List<TvShowData>) {
        val oldSize = this.tvShows.size
        this.tvShows.addAll(tvShows)
        binding.rvTvShows.post { adapter.notifyItemRangeInserted(oldSize, tvShows.size) }
        isMoreDataAvailable = tvShows.isNotEmpty()
    }
}