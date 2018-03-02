package com.sim.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sim.api.common.ServiceException;
import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.PrivilegeMst;
import com.sim.api.model.ProgramMst;
import com.sim.api.model.RoleMst;
import com.sim.api.model.User;
import com.sim.api.service.PrivilegeService;
import com.sim.api.service.ProgramService;
import com.sim.api.service.RoleService;
import com.sim.api.service.UserService;
import com.sim.api.utils.AppUtils;
import com.sim.api.utils.Constants;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/apis/rolePrivilege")
public class RolePrivilegeController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private PrivilegeService privilegeService;

	@Autowired
	private ProgramService programService;
	
	@Autowired
	private AppUtils app;

	private static final Logger LOGGER = Logger.getLogger(RolePrivilegeController.class);
	private static final String CLAIMSTR = "claims";
	private static final String PRODUCES = "application/json;charset=UTF-8";
	private static final String HEADERS = "Accept=text/xml, application/json";
	private static final String ROLEGROUP_CORP = "CORP";

    private String saveRoleAndPrivilegeValidate(String str, HttpServletRequest request, HttpServletResponse response) throws ServiceException{
    	Claims claims = (Claims) request.getAttribute(CLAIMSTR);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(str);
			String mode = mapper.convertValue(node.get("mode"), String.class);
			String roleName = mapper.convertValue(node.get("roleName"), String.class);
			JavaType t = mapper.getTypeFactory().constructCollectionType(List.class, ProgramMst.class);
			List<ProgramMst> programs = mapper.convertValue(node.get("programPrivilegeList"), t);
			
			if(!AppUtils.validateSpecialCharacter(roleName) || !AppUtils.validateSpecialCharacter(mode)){
				app.jsonResponse(response, false, "125");
				return null;
			}

			if(roleName != null  && programs != null ){
				this.saveRoleAndPrivilege(mode, roleName, claims, programs);
			}else{
				throw new ServiceException(999); 
			}
		} catch(Exception ex){
			LOGGER.error(ex);
			throw new ServiceException(ex); 
		}
		return Constants.SUCCESS;	
    }
    private void saveRoleAndPrivilege(String mode,String roleName , Claims claims, List<ProgramMst> programs) throws ServiceException{
		RoleMst roleMst = new RoleMst();
		List<PrivilegeMst> privilegeList ;
		roleMst.setRoleName(roleName);
		
		if("ADD".equals(mode)){
			/** step 1. save role */
			roleMst.setCreatedBy(claims.getSubject());
			roleMst.setLastUpdateBy(claims.getSubject());
			roleService.saveRoleMst(roleMst);
			RoleMst roleMstAfter = roleService.getRoleMstByRoleNameByRoleType(roleMst.getRoleName());


			/** ===========================  save role ========================*/
			/** step 2. save privilege  */
			
			privilegeService.savePrivilegeMst(programs , roleMstAfter , claims.getSubject());
			
			/** Audit record */
			privilegeList = privilegeService.getPrivilegeMstListByRoleId(roleMstAfter.getRoleId());
		}else if("EDIT".equals(mode)){
			RoleMst roleMstAfter = roleService.getRoleMstByRoleNameByRoleType(roleMst.getRoleName());

			privilegeList = privilegeService.getPrivilegeMstListByRoleId(roleMstAfter.getRoleId());
			
			privilegeService.editPrivilegeMst(programs , roleMstAfter , claims.getSubject());

			privilegeList = privilegeService.getPrivilegeMstListByRoleId(roleMstAfter.getRoleId());
		}
    }

    @RequestMapping(value = "saveRoleAndPrivilege", method = RequestMethod.POST, produces=PRODUCES ,headers = {HEADERS})
    @ResponseBody
    public String saveRoleAndPrivilege(@RequestBody String str, final HttpServletRequest request,
    		final HttpServletResponse response) throws ServiceException {
    	String result = null;
    	try {
    		result = this.saveRoleAndPrivilegeValidate(str, request, response);
    	} catch(Exception ex){
    		LOGGER.error(ex);
    		throw new ServiceException(ex); 
    	}
    	return result;
	}

    @RequestMapping(value = "saveRoleAndPrivilegeCorp", method = RequestMethod.POST, produces=PRODUCES ,headers = {HEADERS})
    @ResponseBody
    public String saveRoleAndPrivilegeCorp(@RequestBody String str, final HttpServletRequest request,
    		final HttpServletResponse response) throws ServiceException {
    	String result = null;
    	try {
    		result = this.saveRoleAndPrivilegeValidate(str, request, response);
    	} catch(Exception ex){
    		LOGGER.error(ex);
    		throw new ServiceException(ex); 
    	}
    	return result;
    }
	
	private String convertPrivilegeListToJson(List<PrivilegeMst> privilegeList){
		String result = "";
		int count = 0;
		for(PrivilegeMst model : privilegeList ){
			if(count< privilegeList.size()-1){
				result += model.toJson() +",";
			}else{
				result += model.toJson() ;
			}
			
			count++;
		}
		
		result = " \"PrivilegeMst\" : [" + result + "]";
		
		return result;
	}

	

	@RequestMapping(value = "searchRoleAndPrivilege", method = RequestMethod.POST, produces=PRODUCES ,headers = {HEADERS})
	public DataTable<RoleMst> searchRoleAndPrivilege(@RequestBody SearchDataTable<RoleMst> searchDataTable) throws ServiceException {
		DataTable<RoleMst> result = new DataTable<>();
		try {
			if( searchDataTable.getDataSearch()==null ){
				RoleMst model = new RoleMst();
				searchDataTable.setDataSearch(model);
			}
			result = roleService.searchRoleByDataTable(searchDataTable);
		} catch(Exception ex){
			LOGGER.error(ex);
			throw new ServiceException(ex);  
		}
		return result;
	}
	@RequestMapping(value = "searchRoleAndPrivilegeCorp", method = RequestMethod.POST, produces=PRODUCES ,headers = {HEADERS})
	public DataTable<RoleMst> searchRoleAndPrivilegeCorp(@RequestBody SearchDataTable<RoleMst> searchDataTable) throws ServiceException {
		DataTable<RoleMst> result = new DataTable<>();
		try {
			if( searchDataTable.getDataSearch()==null ){
				RoleMst model = new RoleMst();
				searchDataTable.setDataSearch(model);
			}
			
			result = roleService.searchRoleByDataTable(searchDataTable);
		} catch(Exception ex){
			LOGGER.error(ex);
			throw new ServiceException(ex);  
		}
		return result;
	}
	
	
	@RequestMapping(value = "deleteRoleByRoleName/{roleName}", method = RequestMethod.GET,produces=PRODUCES ,headers = {HEADERS})
	@ResponseBody
	public String deleteKbankRoleByRoleName(final HttpServletRequest request,
			@PathVariable String roleGroup,
			@PathVariable String roleName) throws ServiceException {

		Claims claims = (Claims) request.getAttribute(CLAIMSTR);
		try {
			this.deleteRoleByRoleName(roleGroup, roleName, claims);
		} catch(Exception ex){
			LOGGER.error(ex);
			throw new ServiceException(ex); 
		}
		return Constants.SUCCESS;
	}
	@RequestMapping(value = "deleteCorpRoleByRoleName/{roleGroup}/{roleName}", method = RequestMethod.GET,produces=PRODUCES ,headers = {HEADERS})
	@ResponseBody
	public String deleteCorpRoleByRoleName(final HttpServletRequest request,
			@PathVariable String roleGroup,
			@PathVariable String roleName) throws ServiceException {

		Claims claims = (Claims) request.getAttribute(CLAIMSTR);
		try {
			if(ROLEGROUP_CORP.equals(roleGroup)){
				this.deleteRoleByRoleName(roleGroup, roleName, claims);
			}else{
				throw new ServiceException(126);
			}

		} catch(Exception ex){
			LOGGER.error(ex);
			throw new ServiceException(ex); 
		}
		return Constants.SUCCESS;
	}
	
	private void deleteRoleByRoleName(String roleGroup , String roleName,Claims claims) throws ServiceException{
		String roleType = "";
		RoleMst roleMst = roleService.getRoleMstByRoleNameByRoleType(roleName);
		roleMst.setLastUpdateBy(claims.getSubject());

		roleService.validateAndDeleteRoleByRoleNameByRoleType(roleName);

	}
	
	
	@RequestMapping(value = "searchUserByUserRole", method = RequestMethod.POST, produces=PRODUCES ,headers = {HEADERS})
	public DataTable<User> searchUserByUserRole(@RequestBody SearchDataTable<User> searchDataTable) throws ServiceException {
		DataTable<User> result = new DataTable<>();
		try{

			if(searchDataTable.getDataSearch()==null ){
				User userSearch = new User();
				searchDataTable.setDataSearch(userSearch);
			}	
			
			result = userService.searchUser(searchDataTable);
		}catch(Exception ex){
			LOGGER.error(ex);
			throw new ServiceException(ex);  
		}
		return result;
	}
	@RequestMapping(value = "searchUserByUserRoleCorp", method = RequestMethod.POST, produces=PRODUCES ,headers = {HEADERS})
	public DataTable<User> searchUserByUserRoleCorp(@RequestBody SearchDataTable<User> searchDataTable) throws ServiceException {
		DataTable<User> result = new DataTable<>();
		try{

			if(searchDataTable.getDataSearch()==null ){
				User userSearch = new User();
				searchDataTable.setDataSearch(userSearch);
			}	
			result = userService.searchUser(searchDataTable);
		}catch(Exception ex){
			LOGGER.error(ex);
			throw new ServiceException(ex);  
		}
		return result;
	}

	@RequestMapping(value = "searchProgramMaster", method = RequestMethod.POST, produces=PRODUCES ,headers = {HEADERS})
	public DataTable<ProgramMst> searchProgramMaster(@RequestBody SearchDataTable<ProgramMst> searchDataTable) throws ServiceException {
		DataTable<ProgramMst> result = new DataTable<>();
		try{

			if( searchDataTable.getDataSearch()==null ){
				ProgramMst model = new ProgramMst();
				searchDataTable.setDataSearch(model);
			}
			
			if("EDIT".equals(searchDataTable.getDataSearch().getMode()) || "VIEW".equals(searchDataTable.getDataSearch().getMode())){
				int roldId = searchDataTable.getDataSearch().getRoleId();
				result = programService.searchProgramDefaultPriviligeByRoldId(searchDataTable,roldId);
			}else{
				result = programService.searchProgramByDataTable(searchDataTable);
			}
				
		}catch(Exception ex){
			LOGGER.error(ex);
			throw new ServiceException(ex);  
		}
		return result;
	}
	@RequestMapping(value = "searchProgramMasterCorp", method = RequestMethod.POST, produces=PRODUCES ,headers = {HEADERS})
	public DataTable<ProgramMst> searchProgramMasterCorp(@RequestBody SearchDataTable<ProgramMst> searchDataTable) throws ServiceException {
		DataTable<ProgramMst> result = new DataTable<>();
		try{

			if( searchDataTable.getDataSearch()==null ){
				ProgramMst model = new ProgramMst();
				searchDataTable.setDataSearch(model);
			}
			
			
			if("EDIT".equals(searchDataTable.getDataSearch().getMode()) || "VIEW".equals(searchDataTable.getDataSearch().getMode())){
				int roldId = searchDataTable.getDataSearch().getRoleId();
				result = programService.searchProgramDefaultPriviligeByRoldId(searchDataTable,roldId);
			}else{
				result = programService.searchProgramByDataTable(searchDataTable);
			}
				
		}catch(Exception ex){
			LOGGER.error(ex);
			throw new ServiceException(ex);  
		}
		return result;
	}
}
