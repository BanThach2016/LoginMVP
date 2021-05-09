# LoginMVP
Login MVP using token api

# MVP step by step
ex: SignIn
1. Class: SignInRequest.java
- Dinh nghia cac tham so cua request login 

2. Interface: IUsersService.java
- Dinh nghia cac interface cho api retrofit
- Co 2 cach add token vao header cua request 
  + Them vao moi interface khi khai bao
  + Them vao khi khoi tao doi tuong retrofit 

3. Interface: UserLoginCallback.java
- Dinh nghia phuong thuc callback de xu ly du lieu callback ( ky thuat callback cua java )

4. Class: UserService.java
- Class chua cac phuong thuoc lam viec voi table user ( dang nhap, them, sua, xoa, lay ve...)
- Su dung cac service nao thi khai bao service do
- Can khai bao cac request theo cac service 
- Cac Contructor khac nhau se thiet lap gia tri cho cac serive va cac resquest khac nhau

- Trien khai ham login can cac tham so:
  + Tham so callback: phai khai bao la final, de goi cac ham xu ly callback cua interface buoc 3
  + Token co the them vao o buoc nay ( ham login k can token, cac ham xu ly du lieu phai co )

5. Interface: UserLoginContract.java ( Nen dinh nghia kieu du lieu T de su dung nhieu trong cac class )
- Dinh nghia cac interface co cac ham xu ly cho cac class o Presenter va View
- Day la interface trung gian cho ca 2 class

6. Class: UserLoginPresenter.java 
- Implement interface presenter cua buoc 5 - Interface UserLoginContract
- Cac tham so:
  + view: tham so cua interface o buoc 5, no dc call de set gia tri va se dc goi o acvity
  + service: lop presenter call cac service, dung service nao thi khai bao cai do
- Trien khai cac phuong thuc implement:
  + Khi trien khai se khoi tao cac service can thiet
  + Trong ham trien khai cua service se thiet lap gia tri cho cac view

7. Class: LoginActivity.java 
- La view chinh trong mo hinh MVP
- Implement view trong interface buoc 5. Cac view nay duoc set gia tri o buoc 6. 
- Buoc nay chi cap nhat giao dien va nhan cac su kien de tuong tac cua nguoi dung de call Presenter tuong ung

Finish
