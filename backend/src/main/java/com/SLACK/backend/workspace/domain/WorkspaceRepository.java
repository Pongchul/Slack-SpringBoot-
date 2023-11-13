package com.SLACK.backend.workspace.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface WorkspaceRepository extends Repository<Workspace, Long> {

    Workspace save(Workspace workspace);

    void deleteById(Long id);

    Optional<Workspace> findById(Long id);

    Optional<Workspace> findWorkspacesById(Long id);

    boolean existsById(Long id);

}
