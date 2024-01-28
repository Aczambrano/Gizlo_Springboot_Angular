export interface LoginRequesI {
    usuario: string;
    clave: string;
}


export interface LoginResponseI {
    mensaje: string[];
    error: boolean;
    data: DataI;
}

export interface DataI {
    usuario: UsuarioI;
    token: string;
}

export interface UsuarioI {
    identificador: number;
    nombreUsuario: string;
    apellidoUsuario: string;
    usuario: string;
    correo: string;
    estado: string;
    administrador: boolean;
}
