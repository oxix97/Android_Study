# chapter4

---
## Widget

- textView
- imageView
- RadioGroup, RadioButton
- CheckBox
- ProgressBar
- SeekBar
- RatingBar

---
### TextView

> 화면에 텍스트를 출력하는 가장 기본적인 위젯

- text : 화면에 나타낼 텍스트를 입력하는 속성, 직접 가능하지만 미리 정의해 놓고 사용하는 것을 권장한다.
- textColor : 텍스트 색상 지정할 수 있는 속성
- textSize : 텍스트의 크기를 지정하는 속성 -> sp 사용하며, dimens.xml로 파일을 만들어 적용가능하다.
- textStyle : 텍스트의 스타일을 설정하는 속성
- maxLines : 텍스트뷰에 입력가능한 최대 줄 수 를 설정한다. ex) 1 -> 1줄이 넘어가는 경우 화면에 출력되지 않는다.
- minLines : 텍스트뷰에 입력 가능한 최소 줄 수를 미리 설정한다. -> 적용하지 않을 경우 자동으로 늘어난다.
- singleLine : 텍스트뷰를 한 줄로 보이게 하는 속성이며 maxLines 속성을 1로 설정한 경우와 다른 점은 줄 사이의 \n 를 없애 한 줄로 보이게 한다.
- ellipsize : 텍스트뷰의 문자열이 길어 글자가 잘릴 때 설정한다.
- fontFamily : 글꼴을 지정하는 속성으로 기본 제공 글꼴 및 외부 글꼴 지정 가능하다.
- ems : 텍스트뷰의 크기를 나타낼 때 현재 글꼴의 크기를 기준으로 설정하는 상댓값
  - ex) : 현재 글꼴크기 12sp -> 1em = 12sp, 2em = 24sp

- lines : 텍스트뷰의 높이 고정에 사용한다. -> 글자수가 충족되지 않아도 항상 높이가 고정되어 있어 maxLines와 다르다.

---
### EditText

> 사용자에게서 글자를 입력받는 용도로 사용되는 위젯

- addTextChangedListener : 실시간으로 editText 변경사항을 캐치할 수 있는 리스너이다.
- hint : 클릭하면 사라지는 미리보기를 작성할 수 있다.
- inputType : 입력되는 옵션에 따라 키보드의 모양이 바뀐다. -> ex) inputType : number 는 숫자형 키보드가 나오게 된다.
- imeOptions : 입력 완료 후 실행할 이벤트 설정 -> 키보드의 확인 키 부분에 있는 모양이 달라진다.

---
### ImageView
 
- scaleType : 이미지 크기를 설정하는 속성
- tint : 이미지 영역에 색을 채우는 속성
- alpha : 이미지 투명도 조절

---
### RadioButton

> 라디오 버튼은 여러 개의 선택지 중에서 하나만 선택할 때 사용한다. 라디오 그룹이랑 함께 사용하면 다루기 쉽다.

- setOnCheckedChangeListener : 라디오 그룹의 변경사항을 체크해주는 리스너이다.
- orientation : 가로, 세로로 정렬 여부를 설정한다.
- checkedButton : 미리 선택되어 있는 라디오 버튼을 설정할 수 있다.

---
### CheckBox

> 라디오 버튼과는 다르게 여러 개를 한 번에 선택할 경우 사용한다.

---
### ProgressBar

> 진행 상태를 나타내는 위젯이다. 주로 두 가지로 사용되는데, 현재 진행 중임을 나타내거나, 또 하나는 위와 같은 상황에서 진행 중임과 동시에 얼마나 진행
> 되었는지 진척도를 %로 보여주는 용도로 사용한다.

---
### SeekBar

> 볼륨을 조절하거나 뮤직 플레이어에서 재생 시간을 조절하는 용도로 많이 사용한다.

- OnSeekBarChangeListener
  - seekBar : 현재 시크바 위젯
  - progress : 현재 시크바의 progress 값
  - fromUser : 사용자 터치 여부를 확인하는 값
- max : 시크바의 최대값 설정
- progress : 처음 시작하는 시크바의 값 설정


---
### RatingBar

> 별점을 매기는 위젯이며 시크바와 비슷하다.

- rating : 시작 별점
- fromUser : 사용자 입력 여부
- numStars : 전체 표시되는 별의 개수 설정
- stepSize : 별을 드래그 했을 경우 움직이는 최소 단위