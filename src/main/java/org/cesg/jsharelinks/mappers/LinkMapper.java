package org.cesg.jsharelinks.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.cesg.jsharelinks.models.Link;

public interface LinkMapper {
    final String SELECT_LINK_BY_ID = "SELECT * FROM link WHERE id = #{id}";
    final String SELECT_LINK_ALL = "SELECT * FROM link";
    final String INSERT_LINK = "INSERT INTO link (url,comentario) VALUES (#{url},#{comentario})";
    final String DELETE_LINK = "DELETE FROM link WHERE (id=#{},url=#{},comentario=#{})";

    @Select(SELECT_LINK_BY_ID)
    Link selectLinkById ( final Integer id) throws Exception;

    @Select(SELECT_LINK_ALL)
    List<Link> selectAllLink () throws Exception;

    @Insert(INSERT_LINK)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertLink ( Link link) throws Exception;

    @Delete(DELETE_LINK)
    Integer deleteLink ( Link link) throws Exception;
}
