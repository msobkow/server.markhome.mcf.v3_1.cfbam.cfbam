// Description: Java 25 Table Object interface for CFBam.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamClearTopDepTableObj
{
	public ICFBamSchemaObj getSchema();
	public void setSchema( ICFBamSchemaObj value );

	public void minimizeMemory();

	public String getTableName();
	public String getTableDbName();

	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	public int getClassCode();

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	// public static int getBackingClassCode();

	Class getObjQualifyingClass();

	/**
	 *	Instantiate a new ClearTopDep instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamClearTopDepObj newInstance();

	/**
	 *	Instantiate a new ClearTopDep edition of the specified ClearTopDep instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamClearTopDepEditObj newEditInstance( ICFBamClearTopDepObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamClearTopDepObj realiseClearTopDep( ICFBamClearTopDepObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamClearTopDepObj createClearTopDep( ICFBamClearTopDepObj Obj );

	/**
	 *	Read a ClearTopDep-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The ClearTopDep-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamClearTopDepObj readClearTopDep( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a ClearTopDep-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The ClearTopDep-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamClearTopDepObj readClearTopDep( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamClearTopDepObj readCachedClearTopDep( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeClearTopDep( ICFBamClearTopDepObj obj );

	void deepDisposeClearTopDep( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamClearTopDepObj lockClearTopDep( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the ClearTopDep-derived instances in the database.
	 *
	 *	@return	List of ICFBamClearTopDepObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamClearTopDepObj> readAllClearTopDep();

	/**
	 *	Return a sorted map of all the ClearTopDep-derived instances in the database.
	 *
	 *	@return	List of ICFBamClearTopDepObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamClearTopDepObj> readAllClearTopDep( boolean forceRead );

	List<ICFBamClearTopDepObj> readCachedAllClearTopDep();

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamClearTopDepObj readClearTopDepByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamClearTopDepObj readClearTopDepByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of CFBamScopeObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamClearTopDepObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamClearTopDepObj> readClearTopDepByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Get the map of CFBamClearTopDepObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamClearTopDepObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamClearTopDepObj> readClearTopDepByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamClearDepObj instances sorted by their primary keys for the duplicate ClearDepIdx key.
	 *
	 *	@param	RelationId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamClearTopDepObj cached instances sorted by their primary keys for the duplicate ClearDepIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamClearTopDepObj> readClearTopDepByClearDepIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Get the map of CFBamClearTopDepObj instances sorted by their primary keys for the duplicate ClearDepIdx key.
	 *
	 *	@param	RelationId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamClearTopDepObj cached instances sorted by their primary keys for the duplicate ClearDepIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamClearTopDepObj> readClearTopDepByClearDepIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamClearDepObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamClearTopDepObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamClearTopDepObj> readClearTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamClearTopDepObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamClearTopDepObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamClearTopDepObj> readClearTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamClearTopDepObj instances sorted by their primary keys for the duplicate ClrTopDepTblIdx key.
	 *
	 *	@param	TableId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamClearTopDepObj cached instances sorted by their primary keys for the duplicate ClrTopDepTblIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamClearTopDepObj> readClearTopDepByClrTopDepTblIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Get the map of CFBamClearTopDepObj instances sorted by their primary keys for the duplicate ClrTopDepTblIdx key.
	 *
	 *	@param	TableId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamClearTopDepObj cached instances sorted by their primary keys for the duplicate ClrTopDepTblIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamClearTopDepObj> readClearTopDepByClrTopDepTblIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead );

	/**
	 *	Get the CFBamClearTopDepObj instance for the unique UNameIdx key.
	 *
	 *	@param	TableId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	CFBamClearTopDepObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamClearTopDepObj readClearTopDepByUNameIdx(CFLibDbKeyHash256 TableId,
		String Name );

	/**
	 *	Get the CFBamClearTopDepObj instance for the unique UNameIdx key.
	 *
	 *	@param	TableId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	CFBamClearTopDepObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamClearTopDepObj readClearTopDepByUNameIdx(CFLibDbKeyHash256 TableId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamClearTopDepObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamClearTopDepObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamClearTopDepObj> readClearTopDepByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamClearTopDepObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamClearTopDepObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamClearTopDepObj> readClearTopDepByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamClearTopDepObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamClearTopDepObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamClearTopDepObj> readClearTopDepByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamClearTopDepObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamClearTopDepObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamClearTopDepObj> readClearTopDepByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	ICFBamClearTopDepObj readCachedClearTopDepByIdIdx( CFLibDbKeyHash256 Id );

	List<ICFBamClearTopDepObj> readCachedClearTopDepByTenantIdx( CFLibDbKeyHash256 TenantId );

	List<ICFBamClearTopDepObj> readCachedClearTopDepByClearDepIdx( CFLibDbKeyHash256 RelationId );

	List<ICFBamClearTopDepObj> readCachedClearTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamClearTopDepObj> readCachedClearTopDepByClrTopDepTblIdx( CFLibDbKeyHash256 TableId );

	ICFBamClearTopDepObj readCachedClearTopDepByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name );

	List<ICFBamClearTopDepObj> readCachedClearTopDepByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamClearTopDepObj> readCachedClearTopDepByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeClearTopDepByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeClearTopDepByTenantIdx( CFLibDbKeyHash256 TenantId );

	void deepDisposeClearTopDepByClearDepIdx( CFLibDbKeyHash256 RelationId );

	void deepDisposeClearTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeClearTopDepByClrTopDepTblIdx( CFLibDbKeyHash256 TableId );

	void deepDisposeClearTopDepByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name );

	void deepDisposeClearTopDepByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeClearTopDepByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamClearTopDepObj updateClearTopDep( ICFBamClearTopDepObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteClearTopDep( ICFBamClearTopDepObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The ClearTopDep key attribute of the instance generating the id.
	 */
	void deleteClearTopDepByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The ClearTopDep key attribute of the instance generating the id.
	 */
	void deleteClearTopDepByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	RelationId	The ClearTopDep key attribute of the instance generating the id.
	 */
	void deleteClearTopDepByClearDepIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The ClearTopDep key attribute of the instance generating the id.
	 */
	void deleteClearTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The ClearTopDep key attribute of the instance generating the id.
	 */
	void deleteClearTopDepByClrTopDepTblIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The ClearTopDep key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ClearTopDep key attribute of the instance generating the id.
	 */
	void deleteClearTopDepByUNameIdx(CFLibDbKeyHash256 TableId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The ClearTopDep key attribute of the instance generating the id.
	 */
	void deleteClearTopDepByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The ClearTopDep key attribute of the instance generating the id.
	 */
	void deleteClearTopDepByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Move the CFBamClearTopDepObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamClearTopDepObj refreshed cache instance.
	 */
	ICFBamClearTopDepObj moveUpClearTopDep( ICFBamClearTopDepObj Obj );

	/**
	 *	Move the CFBamClearTopDepObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamClearTopDepObj refreshed cache instance.
	 */
	ICFBamClearTopDepObj moveDownClearTopDep( ICFBamClearTopDepObj Obj );
}
