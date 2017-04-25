package com.emar.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.emar.exception.BussinessException;
import com.emar.model.domain.Template;
import com.emar.sso.bean.User;

/**
 * Custom Template Report
 * 
 * @author franplk
 */
@Repository
public class TemplateDao {

	@Resource(name = "modelTemplate")
	private JdbcTemplate modelTemplate;

	/**
	 * Get Custom Menu Index List
	 */
	public Map<String, String> getMenuIndex(final Long userId) {

		Map<String, String> menuIdexMap = new HashMap<>();

		try {
			String sql = "SELECT board,idxes FROM xv_user_index WHERE uid=?";

			List<Map<String, Object>> menuIdxList = modelTemplate.queryForList(sql, new Object[] { userId });
			for (Map<String, Object> item : menuIdxList) {
				String board = String.valueOf(item.get("board"));
				String idxes = String.valueOf(item.get("idxes"));
				menuIdexMap.put(board, idxes);
			}

		} catch (Exception e) {
		}

		return menuIdexMap;
	}

	/**
	 * Save Or Update Custom Menu Index List
	 */
	public void saveIndex(final User user, final Template t) {
		String board = t.getBoard();

		String old_idx = user.getMenuIndex(board);
		try {
			String sql = "INSERT INTO xv_user_index (idxes,uid,board) values(?,?,?)";
			String idxes = t.getIdxes();
			if (null != old_idx) {
				sql = "UPDATE xv_user_index SET idxes=? WHERE uid=? AND board = ?";
			}
			modelTemplate.update(sql, new Object[] { idxes, user.getUserId(), board });
			user.putMenuIndex(board, idxes);
		} catch (Exception e) {
			throw new BussinessException("Save User Index Failed [user=" + user + "][template=" + t + "]", e);
		}
	}
}
