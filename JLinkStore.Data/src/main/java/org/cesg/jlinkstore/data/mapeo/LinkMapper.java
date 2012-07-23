package org.cesg.jlinkstore.data.mapeo;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.cesg.jlinkstore.data.entidades.Link;

/**
 * Interfas para el mappeo de un link.
 * 
 * @author kristian
 * @version 30.03.2012
 */
public interface LinkMapper {
    final String SELECT_LINK_BY_ID = "SELECT * FROM link WHERE id = #{id}";
    final String SELECT_LINK_ALL = "SELECT * FROM link";
    final String INSERT_LINK = "INSERT INTO link (url,comentario) VALUES (#{url},#{comentario})";
    final String DELETE_LINK = "DELETE FROM link WHERE (id=#{id},url=#{url},comentario=#{comentario})";
    final String DELETE_LINK_BY_ID = "DELETE FROM link WHERE (id=#{id})";

    /**
     * Seleciona un link de la base de datos segun la id.<br>
     * 
     * @param id
     *            'id' del link.
     * @return Entidad asociada al resultado.
     * @throws Exception
     */
    @Select(SELECT_LINK_BY_ID)
    Link selectLinkById ( final Integer id) throws Exception;

    @Select(SELECT_LINK_ALL)
    List<Link> selectAllLink () throws Exception;

    @Insert(INSERT_LINK)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertLink ( Link link) throws Exception;

    @Delete(DELETE_LINK)
    Integer deleteLink ( Link link) throws Exception;

    @Delete(DELETE_LINK_BY_ID)
    Integer deleteLinkById ( Integer id) throws Exception;
}
