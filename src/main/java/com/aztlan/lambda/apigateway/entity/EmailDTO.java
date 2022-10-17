package com.aztlan.lambda.apigateway.entity;
public class EmailDTO {

    private String nombre;
    private String telefono;
    private String correo;
    private String requerimiento;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String direccion) {
		this.telefono = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getRequerimiento() {
		return requerimiento;
	}
	public void setRequerimiento(String requerimiento) {
		this.requerimiento = requerimiento;
	}
	@Override
	public String toString() {
		return "Request [nombre=" + nombre + ", direccion=" + telefono + ", correo=" + correo + ", requerimiento="
				+ requerimiento + "]";
	}
    
}
