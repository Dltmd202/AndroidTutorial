# AndroidTutorial

## View
### SampleView
* 제공된 widget 의 button을 상속한 Mybutton 클래스 정의
* 클릭 이벤트 발생 시 배경색과 글자색을 바꾼다
* 클릭이 취소되거나 끝나는 등의 이벤트 발생시 색을 다시 바꾼다
* invalidate() 메서드를 통해 그래픽을 다시 그린다

### SampleLayout
* LinearLayout을 상속하여 카드뷰에 적용시킬 레이아웃의 xml을 디자인한다
* 이 xml에 대응할 class 파일을 만들고 이의 콘탠츠를 수정할 메서드를 만든다
* 제작한 레이아웃을 카드뷰에 적용시켜 main 액티비티에 적용시킨다
* 카드뷰에 적용시킨 레이아웃의 메서드를 활용한다

## Thread
### SampleThread
* Runnable을 이용한 쓰레드 사용
* Handler를 이용한 UI 변경

## NetWork
### SampleSocket
* 쓰레드의 소켓을 사용하여 로컬네트워크에 접속
* Handler를 이용하여 UI 변경

### SampleHttp
* 쓰레드에서 HttpURLConnection 객체 생성
* Handler를 이용한 UI 변경


### SampleRequest
* Volley를 이용하여 Http 요청 및 응답
* 쓰레드를 사용하지 않고 통신할 수 있도록 도와준다
