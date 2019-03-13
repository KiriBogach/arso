//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.03.12 a las 06:48:08 PM CET 
//


package calificaciones;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="asignatura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="calificacion" type="{http://www.example.org/calificaciones}tipo_calificacion" maxOccurs="unbounded"/>
 *         &lt;element name="diligencia" type="{http://www.example.org/calificaciones}tipo_diligencia" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="curso" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="convocatoria" use="required" type="{http://www.example.org/calificaciones}tipo_convocatoria" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "asignatura",
    "calificacion",
    "diligencia"
})
@XmlRootElement(name = "calificaciones")
public class Calificaciones {

    @XmlElement(required = true)
    protected String asignatura;
    @XmlElement(required = true)
    protected List<TipoCalificacion> calificacion;
    protected List<TipoDiligencia> diligencia;
    @XmlAttribute(name = "curso")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger curso;
    @XmlAttribute(name = "convocatoria", required = true)
    protected TipoConvocatoria convocatoria;

    /**
     * Obtiene el valor de la propiedad asignatura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsignatura() {
        return asignatura;
    }

    /**
     * Define el valor de la propiedad asignatura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsignatura(String value) {
        this.asignatura = value;
    }

    /**
     * Gets the value of the calificacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calificacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalificacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoCalificacion }
     * 
     * 
     */
    public List<TipoCalificacion> getCalificacion() {
        if (calificacion == null) {
            calificacion = new ArrayList<TipoCalificacion>();
        }
        return this.calificacion;
    }

    /**
     * Gets the value of the diligencia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the diligencia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDiligencia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoDiligencia }
     * 
     * 
     */
    public List<TipoDiligencia> getDiligencia() {
        if (diligencia == null) {
            diligencia = new ArrayList<TipoDiligencia>();
        }
        return this.diligencia;
    }

    /**
     * Obtiene el valor de la propiedad curso.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCurso() {
        return curso;
    }

    /**
     * Define el valor de la propiedad curso.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCurso(BigInteger value) {
        this.curso = value;
    }

    /**
     * Obtiene el valor de la propiedad convocatoria.
     * 
     * @return
     *     possible object is
     *     {@link TipoConvocatoria }
     *     
     */
    public TipoConvocatoria getConvocatoria() {
        return convocatoria;
    }

    /**
     * Define el valor de la propiedad convocatoria.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoConvocatoria }
     *     
     */
    public void setConvocatoria(TipoConvocatoria value) {
        this.convocatoria = value;
    }

}
