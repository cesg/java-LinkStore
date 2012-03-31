package org.cesg.jsharelinks.services;

import java.util.List;

import org.cesg.jsharelinks.models.Link;

public interface LinkManager {

    Link selectLink ( final Integer id);

    List<Link> selectAllLink ();

    Integer insertLink ( final Link link);

    Integer deleteLink ( final Link link);
}
