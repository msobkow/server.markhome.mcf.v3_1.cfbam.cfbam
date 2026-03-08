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

public interface ICFBamValueTableObj
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
	 *	Instantiate a new Value instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamValueObj newInstance();

	/**
	 *	Instantiate a new Value edition of the specified Value instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamValueEditObj newEditInstance( ICFBamValueObj orig );

	/**
	 *	Construct an appropriate subclass instance based on the specified class code.
	 *
	 *	@param	backingClassCode	The backing store class code used to identify the proposed instance class.
	 *
	 *	@return	ICFBamValueObj instance, which may be a subclass of a Value instance.
	 */
	ICFBamValueObj constructByClassCode( int backingClassCode );

	/**
	 *	Internal use only.
	 */
	ICFBamValueObj realiseValue( ICFBamValueObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamValueObj createValue( ICFBamValueObj Obj );

	/**
	 *	Read a Value-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Value-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamValueObj readValue( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a Value-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The Value-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamValueObj readValue( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamValueObj readCachedValue( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeValue( ICFBamValueObj obj );

	void deepDisposeValue( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamValueObj lockValue( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the Value-derived instances in the database.
	 *
	 *	@return	List of ICFBamValueObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamValueObj> readAllValue();

	/**
	 *	Return a sorted map of all the Value-derived instances in the database.
	 *
	 *	@return	List of ICFBamValueObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamValueObj> readAllValue( boolean forceRead );

	List<ICFBamValueObj> readCachedAllValue();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Value key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamValueObj readValueByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The Value key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamValueObj readValueByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The Value key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Value key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamValueObj readValueByUNameIdx(CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The Value key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Value key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamValueObj readValueByUNameIdx(CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The Value key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamValueObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamValueObj> readValueByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The Value key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamValueObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamValueObj> readValueByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Value key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamValueObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamValueObj> readValueByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The Value key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamValueObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamValueObj> readValueByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The Value key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamValueObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamValueObj> readValueByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The Value key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamValueObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamValueObj> readValueByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The Value key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamValueObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamValueObj> readValueByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The Value key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamValueObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamValueObj> readValueByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The Value key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Value key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamValueObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamValueObj> readValueByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The Value key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Value key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamValueObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamValueObj> readValueByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The Value key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Value key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamValueObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamValueObj> readValueByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The Value key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Value key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamValueObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamValueObj> readValueByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead );

	ICFBamValueObj readCachedValueByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamValueObj readCachedValueByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamValueObj> readCachedValueByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamValueObj> readCachedValueByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamValueObj> readCachedValueByPrevIdx( CFLibDbKeyHash256 PrevId );

	List<ICFBamValueObj> readCachedValueByNextIdx( CFLibDbKeyHash256 NextId );

	List<ICFBamValueObj> readCachedValueByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	List<ICFBamValueObj> readCachedValueByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	void deepDisposeValueByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeValueByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeValueByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeValueByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeValueByPrevIdx( CFLibDbKeyHash256 PrevId );

	void deepDisposeValueByNextIdx( CFLibDbKeyHash256 NextId );

	void deepDisposeValueByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	void deepDisposeValueByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamValueObj updateValue( ICFBamValueObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteValue( ICFBamValueObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The Value key attribute of the instance generating the id.
	 */
	void deleteValueByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Value key attribute of the instance generating the id.
	 *
	 *	@param	Name	The Value key attribute of the instance generating the id.
	 */
	void deleteValueByUNameIdx(CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Value key attribute of the instance generating the id.
	 */
	void deleteValueByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The Value key attribute of the instance generating the id.
	 */
	void deleteValueByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The Value key attribute of the instance generating the id.
	 */
	void deleteValueByPrevIdx( CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The Value key attribute of the instance generating the id.
	 */
	void deleteValueByNextIdx( CFLibDbKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Value key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The Value key attribute of the instance generating the id.
	 */
	void deleteValueByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The Value key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The Value key attribute of the instance generating the id.
	 */
	void deleteValueByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId );

	/**
	 *	Move the CFBamValueObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamValueObj refreshed cache instance.
	 */
	ICFBamValueObj moveUpValue( ICFBamValueObj Obj );

	/**
	 *	Move the CFBamValueObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamValueObj refreshed cache instance.
	 */
	ICFBamValueObj moveDownValue( ICFBamValueObj Obj );
}
