# Container

---
위젯의 위치를 다룰 때에 레이아웃을 사용했다면 위젯이나 다른 레이아웃에 데이터를 동적으로 표현해줄 때 컨테이너를 사용한다.

컨테이너는 데이터를 반복적으로 표시하는 용도로 사용하며 대표적인 컨테이너로는 List를 화면에 출력할 때 사용하는 RecyclerView가 있다.

---

- Spinner
- RecyclerView

---

### Spinner

![스크린샷 2021-12-23 오후 7 03 58](https://user-images.githubusercontent.com/72330632/147224409-bc2026f5-395b-4dbf-a81e-da5fb11d0e75.png)



> 스피너는 여러 개의 목록 중에서 하나를 선택할 수 있는 선택 도구이다. 마치 버튼이나 텍스트뷰와 같이 작은 위젯처럼
> 보이지만 내부는 복수의 데이터를 처리할 수 있는 컨테이너 구조로 되어있다.

> 스피터는 어뎁터라는 연결 도구를 사용에 화면에 나타낼 데이터와 화면에 보여주는 스피너를 연결한다.
> 여러 개의 데이터가 어뎁터에 입력되면 1개의 데이터당 1개의 아이템 레이아웃이 생성되어 화면에 목록 형태로 나타난다.


```kotlin
var data = listOf("1", "2", "3", "4", "5")
var adapter =
    ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data)
binding.spSpinner.adapter = adapter

binding.spSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        binding.tvResult.text = data.get(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
```

```xml
<androidx.appcompat.widget.AppCompatSpinner
        android:id="@+id/sp_spinner"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:layout_constraintEnd_toEndOf="@id/tv_result"
        app:layout_constraintStart_toStartOf="@id/tv_result"
        app:layout_constraintTop_toBottomOf="@id/tv_result"/>
```

---
### RecyclerView

> 스피너가 조금 더 확장된 형태이다. 컨테이너에 표시될 데이터와 아이템 레이아웃을 어댑터에서 연결해주므로 어댑터에서
> 어떤 아이템 레이아웃을 사용하느냐에 따라 표시되는 모양을 다르게 만들 수 있다.

#### 화면 구성

- Data Class
- RecyclerView Adapter
- ViewHolder

#### Data Class

> 클래스가 data를 보유하면서 아무것도 하지 않는 클래스이다. 하지만 데이터를 조금 더 편하게 사용하라고
> 컴파일러가 여러가지 함수를 제공해준다. 목록은 다음과 같다.

- equals()
- hashCode()
- copy()
- toString()
- componentsN()

#### RecyclerView.Adapter

> 리사이클러뷰는 리사이클러뷰어댑터라는 메서드 어댑터를 사용하여 데이터를 연결한다. 상속이 필요한 구조이며 상속을
> 하면 어댑터와 관련된 대부분의 기능을 사용할 수 있고 추가로 필요한 몇 개의 요소만 개발자가 직접 구현하면 된다.

> 데이터 -> 어댑터 -> 리사이클러뷰

리사이클러뷰어댑터는 개별 데이터에 대응하는 뷰홀더 클래스를 사용한다. 상속하는 리사이클러뷰어댑터에 뷰홀더 클래스를 
제네릭으로 지정해야 하므로 뷰홀더 클래스를 먼저 만들고 나서 어댑터 클래스를 생성하는 것이 더 편하다.

```kotlin
class CustomAdapter : RecyclerView.Adapter<여기에 사용할 뷰홀더 지정>{
    
}
```

아이템이 1000개가 있다고 가정했을 때 1000개를 모두 생성하지 않고 화면에 보이는 만큼의 홀더만 생성하는 것이 특징이다.

#### 어댑터 클래스의 기본 구성

- onCreateViewHolder() : 한 화면에 그려지는 아이템 개수만큼 레이아웃 생성
- getItemCount() : 목록에 보여줄 아이템의 개수
- onBindViewHolder() : 생성된 아이템 레이아웃에 값 입력 후 목록에 출력
- inflate(inflater,parent,attachToRoot)
  - inflater : 바인딩을 생성할 때 사용하는 인플레이터이다. 액티비티에서와는 다르게 LayoutInflater.from을 사용해서 생성해야 한다.
  - parent : 생성되는 바인딩이 속하는 부모 뷰(레이아웃)이다.
  - attachToRoot : true일 경우 attach 해야 하는 대상으로 root를 지정하고 아래에 붙인다. 
  false일 경우 뷰의 최상위 레이아웃의 속성을 기본으로 레이아웃이 적용된다.

#### RecyclerViewAdapter

```kotlin
class CustomAdapter : RecyclerView.Adapter<CustomAdapter.Holder>() {
    var listData = mutableListOf<RecyclerViewData>()

    class Holder(private val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setMemo(data: RecyclerViewData) {
            binding.tvNo.text = "${data.no}"
            binding.tvTitle.text = "${data.title}"
            val sdf = SimpleDateFormat("yyyy/MM/dd")
            val formattedDate = sdf.format(data.timestamp)
            binding.tvTimeStamp.text = formattedDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = listData.get(position)
        holder.setMemo(data)
    }

    override fun getItemCount(): Int = listData.size

}
```
---

#### LayoutManger의 종류

- LinearLayoutManager : 세로 스크롤 및 가로 스크롤을 지원하며 한줄로 목록을 생성합니다.
- GridLayoutManager : 데이터의 사이즈에 따라 그리드의 크기가 결정됩니다. 한줄에 몇 개의 아이템을 표시할 건지 개수를 설정할 수 있습니다.
- StaggeredGridLayoutManager : 컨텍스트를 사용하지 않아 this를 넘기지 않아도 됩니다. 첫 번째 파라미터에서는 한 줄에 표시되는
아이템의 개수, 두 번째 파라미터에서는 세로 방향을 결정한다.

---
#### 목록 클릭 이벤트 처리

> 홀더가 가지고 있는 아이템뷰에 클릭리스너를 달면 해당 목록이 클릭될 때 마다 리스너가 실행된다.

```kotlin
   class Holder(private val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "클릭된 아이템: ${binding.tvNo.text}번째 아이템",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
```