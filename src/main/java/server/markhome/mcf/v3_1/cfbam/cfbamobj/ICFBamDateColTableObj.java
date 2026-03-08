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

public interface ICFBamDateColTableObj
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
	 *	Instantiate a new DateCol instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamDateColObj newInstance();

	/**
	 *	Instantiate a new DateCol edition of the specified DateCol instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamDateColEditObj newEditInstance( ICFBamDateColObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamDateColObj realiseDateCol( ICFBamDateColObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamDateColObj createDateCol( ICFBamDateColObj Obj );

	/**
	 *	Read a DateCol-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DateCol-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDateColObj readDateCol( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a DateCol-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DateCol-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDateColObj readDateCol( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamDateColObj readCachedDateCol( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeDateCol( ICFBamDateColObj obj );

	void deepDisposeDateCol( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamDateColObj lockDateCol( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the DateCol-derived instances in the database.
	 *
	 *	@return	List of ICFBamDateColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDateColObj> readAllDateCol();

	/**
	 *	Return a sorted map of all the DateCol-derived instances in the database.
	 *
	 *	@return	List of ICFBamDateColObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDateColObj> readAllDateCol( boolean forceRead );

	List<ICFBamDateColObj> readCachedAllDateCol();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDateColObj readDateColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDateColObj readDateColByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDateColObj readDateColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamDateColObj readDateColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDateColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDateColObj> readDateColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamDateColObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDateColObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDateColObj> readDateColByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDateColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDateColObj> readDateColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamDateColObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDateColObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDateColObj> readDateColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDateColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDateColObj> readDateColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDateColObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDateColObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDateColObj> readDateColByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDateColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDateColObj> readDateColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDateColObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDateColObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDateColObj> readDateColByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDateColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDateColObj> readDateColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamDateColObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDateColObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDateColObj> readDateColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDateColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDateColObj> readDateColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamDateColObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDateColObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDateColObj> readDateColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDateColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDateColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDateColObj> readDateColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Get the map of CFBamDateColObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDateColObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDateColObj> readDateColByTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead );

	ICFBamDateColObj readCachedDateColByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamDateColObj readCachedDateColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamDateColObj> readCachedDateColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamDateColObj> readCachedDateColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamDateColObj> readCachedDateColByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamDateColObj> readCachedDateColByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamDateColObj> readCachedDateColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamDateColObj> readCachedDateColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	List<ICFBamDateColObj> readCachedDateColByTableIdx( CFLibDbKeyHash256 TableId );

	void deepDisposeDateColByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeDateColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeDateColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeDateColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeDateColByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeDateColByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeDateColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeDateColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeDateColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Internal use only.
	 */
	ICFBamDateColObj updateDateCol( ICFBamDateColObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteDateCol( ICFBamDateColObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The DateCol key attribute of the instance generating the id.
	 */
	void deleteDateColByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@param	Name	The DateCol key attribute of the instance generating the id.
	 */
	void deleteDateColByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DateCol key attribute of the instance generating the id.
	 */
	void deleteDateColByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The DateCol key attribute of the instance generating the id.
	 */
	void deleteDateColByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The DateCol key attribute of the instance generating the id.
	 */
	void deleteDateColByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The DateCol key attribute of the instance generating the id.
	 */
	void deleteDateColByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The DateCol key attribute of the instance generating the id.
	 */
	void deleteDateColByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The DateCol key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The DateCol key attribute of the instance generating the id.
	 */
	void deleteDateColByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The DateCol key attribute of the instance generating the id.
	 */
	void deleteDateColByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Move the CFBamDateColObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDateColObj refreshed cache instance.
	 */
	ICFBamDateColObj moveUpDateCol( ICFBamDateColObj Obj );

	/**
	 *	Move the CFBamDateColObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamDateColObj refreshed cache instance.
	 */
	ICFBamDateColObj moveDownDateCol( ICFBamDateColObj Obj );
}
