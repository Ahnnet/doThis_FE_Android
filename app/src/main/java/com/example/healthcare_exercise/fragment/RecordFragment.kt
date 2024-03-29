package com.example.healthcare_exercise.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcare_exercise.recyclerView.RecordAdapter
import com.example.healthcare_exercise.recyclerView.RecordData
import com.example.healthcare_exercise.databinding.FragmentRecordBinding
import com.example.healthcare_exercise.recyclerView.ListViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.ValueEventListener
//import com.google.firebase.ktx.database


class RecordFragment : Fragment() {
    ///////////
    private lateinit var adapter: RecordAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(ListViewModel::class.java)}

    ///////////
    lateinit var binding: FragmentRecordBinding
    lateinit var recordAdapter: RecordAdapter
    val datas = mutableListOf<RecordData>()
    private lateinit var database: DatabaseReference
    val testPath = "temp/result/drj9802@gmail_com"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordBinding.inflate(inflater, container, false)

        // Firebase realTime Database 연결
        Log.d("파베", "시작")
        // realTime DB
        database = Firebase.database.getReference();


        database.child(testPath).get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
//            Log.i("firebase", "Got value ${it.children.forEach(RecordData(result = it.))}")
            Log.i("firebase", "Got value ${it.childrenCount}")
            Log.i("firebase", "Got value ${it.children.elementAt(0).value.toString()}")
            Log.i("firebase", "Got value ${it.children.elementAt(7).value.toString()}")
            Log.i("firebase", "Got value ${it.children.elementAt(13).value.toString()}")
//            Log.i("firebase", "Got value ${it.children.elementAt(13).getValue() as RecordData}")
//            datas.add((it.children.elementAt(0).value.apply {  } as RecordData))
            Log.i("aa","ㅅㅅ시작")
            datas.add(it.children.elementAt(0).value as RecordData)
            Log.i("aa","ㅅㅅ1111")
            datas.add(it.children.elementAt(1).value as RecordData)
            datas.add(it.children.elementAt(2) as RecordData)
            datas.add(it.children.elementAt(3) as RecordData)
            Log.i("aa","굳")

        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }





        // recycler view 호출
//        context?.let { initRecordRecycler(it) }

        return binding.root
    }

    private fun initRecordRecycler(context: Context) {

        adapter = RecordAdapter(context)
        val recyclerView: RecyclerView = binding.rvRecord
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        observerData()
    }
    fun observerData(){
        viewModel.fetchData().observe(viewLifecycleOwner, Observer{
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })








//        // Firebase realTime Database 연결
//        Log.d("파베", "시작")
//        // realTime DB
//        val testPath = "temp/result/drj9802@gmail_com"
////        database = Firebase.database.getReference().child("/temp/result/drj9802@gmail_com/exercise_Squat_2305092239");
//        database = Firebase.database.getReference().child(testPath);
//        // Adapter 선언
//        recordAdapter = RecordAdapter(context)
//        binding.rvRecord.adapter = recordAdapter
//
//        database.addValueEventListener(object: ValueEventListener{
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("파베리스트","캔슬에러")
//            }
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for(data in dataSnapshot.children){
//                    var record = data.getValue(RecordData::class.java)
////                    val path2 = data.value.toString()
////                    var database2 = Firebase.database.getReference().child(testPath+"/"+path2)
//                    Log.d("파베리스트",record?.result.toString()+"  => 출력")
//
////                    val modelResult = data.getValue(RecordData::class.java)
////                    datas.add(RecordData(result=" 89%", num=recordAdapter.itemCount.toString(), method = "SQUAT1", date = "yymmdd"))
//                }
//            }
//        })
//
////        datas.apply{
////            add(RecordData(result=" 89%", num=recordAdapter.itemCount.toString(), part = "SQUAT1", date = "yymmdd"))
////            add(RecordData(result=" 36%", num=recordAdapter.itemCount.toString(), part = "SQUAT2", date = "yymmdd"))
////            add(RecordData(result=" 75%", num=recordAdapter.itemCount.toString(), part = "SQUAT3", date = "yymmdd"))
////            add(RecordData(result=" 66%", num=recordAdapter.itemCount.toString(), part = "SQUAT4", date = "yymmdd"))
//
//            recordAdapter.datas = datas
//            recordAdapter.notifyDataSetChanged()
////        }
    }
}

private fun <T> MutableIterable<T>.forEach(action: RecordData) {

}
