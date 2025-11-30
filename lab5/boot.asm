org 0x7C00
boot:
    push cs
    pop ax
    mov ds,ax
    mov es,ax
    mov ss,ax
    mov sp,0x7C00

    mov ax,3
    int 0x10

    mov ah, 6
    xor al, al
    mov bh, 0xF4
    mov cx, 0
    mov dx, 0x184F
    int 0x10

    mov bh, 0
    mov bl, 0x04

    mov si,logo_line1
    call center_print
    mov si,logo_line2
    call center_print
    mov si,logo_line3
    call center_print
    call print_newline

    mov si,msg_hello
    call center_print

    mov si,msg_ask_name
    call print_string
    mov di,input_name
    call read_string

    mov si,msg_welcome
    call print_string
    mov si,input_name
    call print_string
    call print_newline

    mov si,msg_ask_age
    call print_string

    mov di,input_age_str
    mov cx,6
    xor ax,ax
    rep stosb

    mov di,input_age_str
    call read_string

    mov si,input_age_str
    call string_to_number
    mov bx,ax

    mov ax,2025
    sub ax,bx
    mov [birth_year],ax

    mov si,msg_birth_year_prefix
    call print_string
    mov ax,[birth_year]
    call print_number
    call print_newline

    hlt
    jmp $

print_string:
    lodsb
    or al,al
    jz .e
    mov ah,0x0E
    mov bh,0
    mov bl,0x04
    int 0x10
    jmp print_string
.e: ret

print_newline:
    mov ah,0x0E
    mov al,13
    int 0x10
    mov al,10
    int 0x10
    ret

current_row db 3

center_print:
    push si
    xor cx,cx
.cnt: lodsb
    or al,al
    jz .len
    inc cx
    jmp .cnt
.len:
    mov ax,80
    sub ax,cx
    shr ax,1
    mov dh,[current_row]
    mov dl,al
    mov ah,2
    int 0x10
    pop si
    call print_string
    call print_newline
    inc byte [current_row]
    ret

read_string:
    mov cx,0
.r: mov ah,0
    int 0x16
    cmp al,13
    je .done
    cmp al,8
    je .bs
    cmp cx,15
    jge .r
    mov ah,0x0E
    mov bh,0
    mov bl,0x04
    int 0x10
    stosb
    inc cx
    jmp .r
.bs: cmp cx,0
    jz .r
    dec di
    dec cx
    mov ah,0x0E
    mov al,8
    int 0x10
    mov al,' '
    int 0x10
    mov al,8
    int 0x10
    jmp .r
.done:
    mov al,0
    stosb
    mov ah,0x0E
    mov al,13
    int 0x10
    mov al,10
    int 0x10
    ret

string_to_number:
    xor ax,ax
    mov [res],ax
.sln: lodsb
    or al,al
    jz .fin
    cmp al,'0'
    jb .sln
    cmp al,'9'
    ja .sln
    sub al,'0'
    mov bl,al
    mov ax,[res]
    xor dx,dx
    mov cx,10
    mul cx
    add ax,bx
    mov [res],ax
    jmp .sln
.fin:
    mov ax,[res]
    ret

res dw 0

print_number:
    cmp ax,0
    jne .cc
    mov ah,0x0E
    mov al,'0'
    int 0x10
    ret
.cc:
    mov cx,0
    mov bx,10
.l1:
    xor dx,dx
    div bx
    push dx
    inc cx
    test ax,ax
    jnz .l1
.l2:
    pop dx
    add dl,'0'
    mov ah,0x0E
    mov al,dl
    mov bh,0
    mov bl,0x04
    int 0x10
    loop .l2
    ret

logo_line1 db ' __  __ ',0
logo_line2 db '|  ||__ ',0
logo_line3 db '|__| __|',0

msg_hello db 'Welcome to OS!',0
msg_ask_name db 'Enter your name: ',0
msg_welcome db 'Hello, ',0
msg_ask_age db 'Enter your age (full years): ',0
msg_birth_year_prefix db 'Birth year: ',0

input_name times 16 db 0
input_age_str times 6 db 0
birth_year dw 0

times 510-($-$$) db 0
dw 0xAA55
