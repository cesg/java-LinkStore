package org.cesg.jsharelinks.models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Link implements Serializable {

    private Integer id;
    private String url;
    private String comentario;

    /**
     * @param id
     * @param url
     * @param comentario
     */
    public Link ( Integer id , String url , String comentario) {
        super();
        this.id = id;
        this.url = url;
        this.comentario = comentario;
    }

    /**
     * 
     */
    public Link () {
        super();
    }

    public final Integer getId () {
        return id;
    }

    public final void setId ( Integer id) {
        this.id = id;
    }

    public final String getUrl () {
        return url;
    }

    public final void setUrl ( String url) {
        this.url = url;
    }

    public final String getComentario () {
        return comentario;
    }

    public final void setComentario ( String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder();
        builder.append("Link [id=");
        builder.append(id);
        builder.append(", url=");
        builder.append(url);
        builder.append(", comentario=");
        builder.append(comentario);
        builder.append("]");
        return builder.toString();
    }

    // @Override
    // public int hashCode () {
    // final int prime = 31;
    // int result = 1;
    // result = prime * result + ((id == null) ? 0 : id.hashCode());
    // return result;
    // }

    @Override
    public boolean equals ( Object obj) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        Link other = ( Link ) obj;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        } else if ( !id.equals(other.id) )
            return false;
        return true;
    }

}
