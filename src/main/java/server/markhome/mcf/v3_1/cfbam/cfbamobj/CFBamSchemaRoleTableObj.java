// Description: Java 25 Table Object implementation for SchemaRole.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
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

public class CFBamSchemaRoleTableObj
	implements ICFBamSchemaRoleTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamSchemaRole.CLASS_CODE;
	protected static final int backingClassCode = ICFBamSchemaRole.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj> members;
	private Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj> allSchemaRole;
	private Map< ICFBamRoleDefByUNameIdxKey,
		ICFBamSchemaRoleObj > indexByUNameIdx;
	private Map< ICFBamRoleDefByScopeIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj > > indexByScopeIdx;
	private Map< ICFBamRoleDefByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj > > indexByDefSchemaIdx;
	private Map< ICFBamRoleDefByUDefIdxKey,
		ICFBamSchemaRoleObj > indexByUDefIdx;
	private Map< ICFBamSchemaRoleBySchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj > > indexBySchemaIdx;
	public static String TABLE_NAME = "SchemaRole";
	public static String TABLE_DBNAME = "schrole";

	public CFBamSchemaRoleTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamSchemaRoleObj>();
		allSchemaRole = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByUDefIdx = null;
		indexBySchemaIdx = null;
	}

	public CFBamSchemaRoleTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamSchemaRoleObj>();
		allSchemaRole = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByUDefIdx = null;
		indexBySchemaIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamSchemaRoleTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamSchemaRoleTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allSchemaRole = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByUDefIdx = null;
		indexBySchemaIdx = null;
		List<ICFBamSchemaRoleObj> toForget = new LinkedList<ICFBamSchemaRoleObj>();
		ICFBamSchemaRoleObj cur = null;
		Iterator<ICFBamSchemaRoleObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSchemaRoleObj.
	 */
	@Override
	public ICFBamSchemaRoleObj newInstance() {
		ICFBamSchemaRoleObj inst = new CFBamSchemaRoleObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSchemaRoleObj.
	 */
	@Override
	public ICFBamSchemaRoleEditObj newEditInstance( ICFBamSchemaRoleObj orig ) {
		ICFBamSchemaRoleEditObj edit = new CFBamSchemaRoleEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamSchemaRoleObj realiseSchemaRole( ICFBamSchemaRoleObj Obj ) {
		ICFBamSchemaRoleObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamSchemaRoleObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamSchemaRoleObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUNameIdx != null ) {
				ICFBamRoleDefByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByScopeIdx != null ) {
				ICFBamRoleDefByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					indexByScopeIdx.remove( keyScopeIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamRoleDefByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByUDefIdx != null ) {
				ICFBamRoleDefByUDefIdxKey keyUDefIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByUDefIdxKey();
				keyUDefIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUDefIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				keyUDefIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUDefIdx.remove( keyUDefIdx );
			}

			if( indexBySchemaIdx != null ) {
				ICFBamSchemaRoleBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactorySchemaRole().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.remove( keepObj.getPKey() );
					if( mapSchemaIdx.size() <= 0 ) {
						indexBySchemaIdx.remove( keySchemaIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamSchemaRoleObj)schema.getRoleDefTableObj().realiseRoleDef( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUNameIdx != null ) {
				ICFBamRoleDefByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByScopeIdx != null ) {
				ICFBamRoleDefByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamRoleDefByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUDefIdx != null ) {
				ICFBamRoleDefByUDefIdxKey keyUDefIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByUDefIdxKey();
				keyUDefIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUDefIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				keyUDefIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUDefIdx.put( keyUDefIdx, keepObj );
			}

			if( indexBySchemaIdx != null ) {
				ICFBamSchemaRoleBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactorySchemaRole().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSchemaRole != null ) {
				allSchemaRole.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamSchemaRoleObj)schema.getRoleDefTableObj().realiseRoleDef( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSchemaRole != null ) {
				allSchemaRole.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUNameIdx != null ) {
				ICFBamRoleDefByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByScopeIdx != null ) {
				ICFBamRoleDefByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamRoleDefByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUDefIdx != null ) {
				ICFBamRoleDefByUDefIdxKey keyUDefIdx =
					schema.getCFBamBackingStore().getFactoryRoleDef().newByUDefIdxKey();
				keyUDefIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUDefIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				keyUDefIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUDefIdx.put( keyUDefIdx, keepObj );
			}

			if( indexBySchemaIdx != null ) {
				ICFBamSchemaRoleBySchemaIdxKey keySchemaIdx =
					schema.getCFBamBackingStore().getFactorySchemaRole().newBySchemaIdxKey();
				keySchemaIdx.setRequiredSchemaDefId( keepObj.getRequiredSchemaDefId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj > mapSchemaIdx = indexBySchemaIdx.get( keySchemaIdx );
				if( mapSchemaIdx != null ) {
					mapSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamSchemaRoleObj createSchemaRole( ICFBamSchemaRoleObj Obj ) {
		ICFBamSchemaRoleObj obj = Obj;
		ICFBamSchemaRole rec = obj.getSchemaRoleRec();
		schema.getCFBamBackingStore().getTableSchemaRole().createSchemaRole(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamSchemaRoleObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamSchemaRoleObj readSchemaRole( CFLibDbKeyHash256 pkey ) {
		return( readSchemaRole( pkey, false ) );
	}

	@Override
	public ICFBamSchemaRoleObj readSchemaRole( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamSchemaRoleObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamSchemaRole readRec = schema.getCFBamBackingStore().getTableSchemaRole().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamSchemaRoleObj)schema.getRoleDefTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamSchemaRoleObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamSchemaRoleObj readCachedSchemaRole( CFLibDbKeyHash256 pkey ) {
		ICFBamSchemaRoleObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSchemaRole( ICFBamSchemaRoleObj obj )
	{
		final String S_ProcName = "CFBamSchemaRoleTableObj.reallyDeepDisposeSchemaRole() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamSchemaRoleObj existing = readCachedSchemaRole( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamSchemaRoleBySchemaIdxKey keySchemaIdx = schema.getCFBamBackingStore().getFactorySchemaRole().newBySchemaIdxKey();
		keySchemaIdx.setRequiredSchemaDefId( existing.getRequiredSchemaDefId() );



		if( indexBySchemaIdx != null ) {
			if( indexBySchemaIdx.containsKey( keySchemaIdx ) ) {
				indexBySchemaIdx.get( keySchemaIdx ).remove( pkey );
				if( indexBySchemaIdx.get( keySchemaIdx ).size() <= 0 ) {
					indexBySchemaIdx.remove( keySchemaIdx );
				}
			}
		}


		schema.getRoleDefTableObj().reallyDeepDisposeRoleDef( obj );
	}
	@Override
	public void deepDisposeSchemaRole( CFLibDbKeyHash256 pkey ) {
		ICFBamSchemaRoleObj obj = readCachedSchemaRole( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamSchemaRoleObj lockSchemaRole( CFLibDbKeyHash256 pkey ) {
		ICFBamSchemaRoleObj locked = null;
		ICFBamSchemaRole lockRec = schema.getCFBamBackingStore().getTableSchemaRole().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamSchemaRoleObj)schema.getRoleDefTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamSchemaRoleObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSchemaRole", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamSchemaRoleObj> readAllSchemaRole() {
		return( readAllSchemaRole( false ) );
	}

	@Override
	public List<ICFBamSchemaRoleObj> readAllSchemaRole( boolean forceRead ) {
		final String S_ProcName = "readAllSchemaRole";
		if( ( allSchemaRole == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj> map = new HashMap<CFLibDbKeyHash256,ICFBamSchemaRoleObj>();
			allSchemaRole = map;
			ICFBamSchemaRole[] recList = schema.getCFBamBackingStore().getTableSchemaRole().readAllDerived( null );
			ICFBamSchemaRole rec;
			ICFBamSchemaRoleObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaRoleObj)schema.getRoleDefTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaRoleObj realised = (ICFBamSchemaRoleObj)obj.realise();
			}
		}
		int len = allSchemaRole.size();
		ICFBamSchemaRoleObj arr[] = new ICFBamSchemaRoleObj[len];
		Iterator<ICFBamSchemaRoleObj> valIter = allSchemaRole.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaRoleObj> arrayList = new ArrayList<ICFBamSchemaRoleObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaRoleObj> cmp = new Comparator<ICFBamSchemaRoleObj>() {
			@Override
			public int compare( ICFBamSchemaRoleObj lhs, ICFBamSchemaRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaRoleObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamSchemaRoleObj> readCachedAllSchemaRole() {
		final String S_ProcName = "readCachedAllSchemaRole";
		ArrayList<ICFBamSchemaRoleObj> arrayList = new ArrayList<ICFBamSchemaRoleObj>();
		if( allSchemaRole != null ) {
			int len = allSchemaRole.size();
			ICFBamSchemaRoleObj arr[] = new ICFBamSchemaRoleObj[len];
			Iterator<ICFBamSchemaRoleObj> valIter = allSchemaRole.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamSchemaRoleObj> cmp = new Comparator<ICFBamSchemaRoleObj>() {
			public int compare( ICFBamSchemaRoleObj lhs, ICFBamSchemaRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamSchemaRoleObj readSchemaRoleByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readSchemaRoleByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamSchemaRoleObj readSchemaRoleByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamSchemaRoleObj obj = readSchemaRole( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamSchemaRoleObj readSchemaRoleByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		return( readSchemaRoleByUNameIdx( ScopeId,
			Name,
			false ) );
	}

	@Override
	public ICFBamSchemaRoleObj readSchemaRoleByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamRoleDefByUNameIdxKey,
				ICFBamSchemaRoleObj >();
		}
		ICFBamRoleDefByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamSchemaRoleObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamRoleDef rec = schema.getCFBamBackingStore().getTableRoleDef().readDerivedByUNameIdx( null,
				ScopeId,
				Name );
			if( rec != null ) {
				obj = (ICFBamSchemaRoleObj)schema.getRoleDefTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamSchemaRoleObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamSchemaRoleObj> readSchemaRoleByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		return( readSchemaRoleByScopeIdx( ScopeId,
			false ) );
	}

	@Override
	public List<ICFBamSchemaRoleObj> readSchemaRoleByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaRoleByScopeIdx";
		ICFBamRoleDefByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj> dict;
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamRoleDefByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRoleObj > >();
		}
		if( ( ! forceRead ) && indexByScopeIdx.containsKey( key ) ) {
			dict = indexByScopeIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaRoleObj>();
			ICFBamRoleDefObj obj;
			ICFBamRoleDef[] recList = schema.getCFBamBackingStore().getTableRoleDef().readDerivedByScopeIdx( null,
				ScopeId );
			ICFBamRoleDef rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaRoleObj)schema.getRoleDefTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaRoleObj realised = (ICFBamSchemaRoleObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByScopeIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaRoleObj arr[] = new ICFBamSchemaRoleObj[len];
		Iterator<ICFBamSchemaRoleObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaRoleObj> arrayList = new ArrayList<ICFBamSchemaRoleObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaRoleObj> cmp = new Comparator<ICFBamSchemaRoleObj>() {
			@Override
			public int compare( ICFBamSchemaRoleObj lhs, ICFBamSchemaRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaRoleObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamSchemaRoleObj> readSchemaRoleByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readSchemaRoleByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamSchemaRoleObj> readSchemaRoleByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaRoleByDefSchemaIdx";
		ICFBamRoleDefByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamRoleDefByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRoleObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaRoleObj>();
			ICFBamRoleDefObj obj;
			ICFBamRoleDef[] recList = schema.getCFBamBackingStore().getTableRoleDef().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamRoleDef rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaRoleObj)schema.getRoleDefTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaRoleObj realised = (ICFBamSchemaRoleObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaRoleObj arr[] = new ICFBamSchemaRoleObj[len];
		Iterator<ICFBamSchemaRoleObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaRoleObj> arrayList = new ArrayList<ICFBamSchemaRoleObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaRoleObj> cmp = new Comparator<ICFBamSchemaRoleObj>() {
			@Override
			public int compare( ICFBamSchemaRoleObj lhs, ICFBamSchemaRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaRoleObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamSchemaRoleObj readSchemaRoleByUDefIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		return( readSchemaRoleByUDefIdx( ScopeId,
			DefSchemaId,
			Name,
			false ) );
	}

	@Override
	public ICFBamSchemaRoleObj readSchemaRoleByUDefIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name, boolean forceRead )
	{
		if( indexByUDefIdx == null ) {
			indexByUDefIdx = new HashMap< ICFBamRoleDefByUDefIdxKey,
				ICFBamSchemaRoleObj >();
		}
		ICFBamRoleDefByUDefIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByUDefIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalDefSchemaId( DefSchemaId );
		key.setRequiredName( Name );
		ICFBamSchemaRoleObj obj = null;
		if( ( ! forceRead ) && indexByUDefIdx.containsKey( key ) ) {
			obj = indexByUDefIdx.get( key );
		}
		else {
			ICFBamRoleDef rec = schema.getCFBamBackingStore().getTableRoleDef().readDerivedByUDefIdx( null,
				ScopeId,
				DefSchemaId,
				Name );
			if( rec != null ) {
				obj = (ICFBamSchemaRoleObj)schema.getRoleDefTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamSchemaRoleObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamSchemaRoleObj> readSchemaRoleBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		return( readSchemaRoleBySchemaIdx( SchemaDefId,
			false ) );
	}

	@Override
	public List<ICFBamSchemaRoleObj> readSchemaRoleBySchemaIdx( CFLibDbKeyHash256 SchemaDefId,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaRoleBySchemaIdx";
		ICFBamSchemaRoleBySchemaIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRole().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj> dict;
		if( indexBySchemaIdx == null ) {
			indexBySchemaIdx = new HashMap< ICFBamSchemaRoleBySchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRoleObj > >();
		}
		if( ( ! forceRead ) && indexBySchemaIdx.containsKey( key ) ) {
			dict = indexBySchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaRoleObj>();
			ICFBamSchemaRoleObj obj;
			ICFBamSchemaRole[] recList = schema.getCFBamBackingStore().getTableSchemaRole().readDerivedBySchemaIdx( null,
				SchemaDefId );
			ICFBamSchemaRole rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaRoleObj)schema.getRoleDefTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaRoleObj realised = (ICFBamSchemaRoleObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaRoleObj arr[] = new ICFBamSchemaRoleObj[len];
		Iterator<ICFBamSchemaRoleObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaRoleObj> arrayList = new ArrayList<ICFBamSchemaRoleObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaRoleObj> cmp = new Comparator<ICFBamSchemaRoleObj>() {
			@Override
			public int compare( ICFBamSchemaRoleObj lhs, ICFBamSchemaRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaRoleObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamSchemaRoleObj readCachedSchemaRoleByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamSchemaRoleObj obj = null;
		obj = readCachedSchemaRole( Id );
		return( obj );
	}

	@Override
	public ICFBamSchemaRoleObj readCachedSchemaRoleByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamSchemaRoleObj obj = null;
		ICFBamRoleDefByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamSchemaRoleObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamSchemaRoleObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamSchemaRoleObj> readCachedSchemaRoleByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "readCachedSchemaRoleByScopeIdx";
		ICFBamRoleDefByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		ArrayList<ICFBamSchemaRoleObj> arrayList = new ArrayList<ICFBamSchemaRoleObj>();
		if( indexByScopeIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj> dict;
			if( indexByScopeIdx.containsKey( key ) ) {
				dict = indexByScopeIdx.get( key );
				int len = dict.size();
				ICFBamSchemaRoleObj arr[] = new ICFBamSchemaRoleObj[len];
				Iterator<ICFBamSchemaRoleObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamSchemaRoleObj obj;
			Iterator<ICFBamSchemaRoleObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaRoleObj> cmp = new Comparator<ICFBamSchemaRoleObj>() {
			@Override
			public int compare( ICFBamSchemaRoleObj lhs, ICFBamSchemaRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamSchemaRoleObj> readCachedSchemaRoleByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedSchemaRoleByDefSchemaIdx";
		ICFBamRoleDefByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamSchemaRoleObj> arrayList = new ArrayList<ICFBamSchemaRoleObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamSchemaRoleObj arr[] = new ICFBamSchemaRoleObj[len];
				Iterator<ICFBamSchemaRoleObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamSchemaRoleObj obj;
			Iterator<ICFBamSchemaRoleObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaRoleObj> cmp = new Comparator<ICFBamSchemaRoleObj>() {
			@Override
			public int compare( ICFBamSchemaRoleObj lhs, ICFBamSchemaRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamSchemaRoleObj readCachedSchemaRoleByUDefIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		ICFBamSchemaRoleObj obj = null;
		ICFBamRoleDefByUDefIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByUDefIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalDefSchemaId( DefSchemaId );
		key.setRequiredName( Name );
		if( indexByUDefIdx != null ) {
			if( indexByUDefIdx.containsKey( key ) ) {
				obj = indexByUDefIdx.get( key );
			}
			else {
				Iterator<ICFBamSchemaRoleObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamSchemaRoleObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamSchemaRoleObj> readCachedSchemaRoleBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		final String S_ProcName = "readCachedSchemaRoleBySchemaIdx";
		ICFBamSchemaRoleBySchemaIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRole().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		ArrayList<ICFBamSchemaRoleObj> arrayList = new ArrayList<ICFBamSchemaRoleObj>();
		if( indexBySchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj> dict;
			if( indexBySchemaIdx.containsKey( key ) ) {
				dict = indexBySchemaIdx.get( key );
				int len = dict.size();
				ICFBamSchemaRoleObj arr[] = new ICFBamSchemaRoleObj[len];
				Iterator<ICFBamSchemaRoleObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamSchemaRoleObj obj;
			Iterator<ICFBamSchemaRoleObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaRoleObj> cmp = new Comparator<ICFBamSchemaRoleObj>() {
			@Override
			public int compare( ICFBamSchemaRoleObj lhs, ICFBamSchemaRoleObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSchemaRoleByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamSchemaRoleObj obj = readCachedSchemaRoleByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSchemaRoleByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamSchemaRoleObj obj = readCachedSchemaRoleByUNameIdx( ScopeId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSchemaRoleByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "deepDisposeSchemaRoleByScopeIdx";
		ICFBamSchemaRoleObj obj;
		List<ICFBamSchemaRoleObj> arrayList = readCachedSchemaRoleByScopeIdx( ScopeId );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaRoleObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSchemaRoleByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeSchemaRoleByDefSchemaIdx";
		ICFBamSchemaRoleObj obj;
		List<ICFBamSchemaRoleObj> arrayList = readCachedSchemaRoleByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaRoleObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSchemaRoleByUDefIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		ICFBamSchemaRoleObj obj = readCachedSchemaRoleByUDefIdx( ScopeId,
				DefSchemaId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSchemaRoleBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		final String S_ProcName = "deepDisposeSchemaRoleBySchemaIdx";
		ICFBamSchemaRoleObj obj;
		List<ICFBamSchemaRoleObj> arrayList = readCachedSchemaRoleBySchemaIdx( SchemaDefId );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaRoleObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamSchemaRoleObj updateSchemaRole( ICFBamSchemaRoleObj Obj ) {
		ICFBamSchemaRoleObj obj = Obj;
		schema.getCFBamBackingStore().getTableSchemaRole().updateSchemaRole( null,
			Obj.getSchemaRoleRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getSchemaRoleTableObj().getClassCode() ) {
			obj = (ICFBamSchemaRoleObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSchemaRole( ICFBamSchemaRoleObj Obj ) {
		ICFBamSchemaRoleObj obj = Obj;
		schema.getCFBamBackingStore().getTableSchemaRole().deleteSchemaRole( null,
			obj.getSchemaRoleRec() );
		Obj.forget();
	}

	@Override
	public void deleteSchemaRoleByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamSchemaRoleObj obj = readSchemaRole(Id);
		if( obj != null ) {
			ICFBamSchemaRoleEditObj editObj = (ICFBamSchemaRoleEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamSchemaRoleEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeSchemaRoleByIdIdx( Id );
	}

	@Override
	public void deleteSchemaRoleByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamRoleDefByUNameIdxKey,
				ICFBamSchemaRoleObj >();
		}
		ICFBamRoleDefByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamSchemaRoleObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaRole().deleteSchemaRoleByUNameIdx( null,
				ScopeId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaRole().deleteSchemaRoleByUNameIdx( null,
				ScopeId,
				Name );
		}
		deepDisposeSchemaRoleByUNameIdx( ScopeId,
				Name );
	}

	@Override
	public void deleteSchemaRoleByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		ICFBamRoleDefByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamRoleDefByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRoleObj > >();
		}
		if( indexByScopeIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj> dict = indexByScopeIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaRole().deleteSchemaRoleByScopeIdx( null,
				ScopeId );
			Iterator<ICFBamSchemaRoleObj> iter = dict.values().iterator();
			ICFBamSchemaRoleObj obj;
			List<ICFBamSchemaRoleObj> toForget = new LinkedList<ICFBamSchemaRoleObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByScopeIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaRole().deleteSchemaRoleByScopeIdx( null,
				ScopeId );
		}
		deepDisposeSchemaRoleByScopeIdx( ScopeId );
	}

	@Override
	public void deleteSchemaRoleByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamRoleDefByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamRoleDefByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRoleObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaRole().deleteSchemaRoleByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamSchemaRoleObj> iter = dict.values().iterator();
			ICFBamSchemaRoleObj obj;
			List<ICFBamSchemaRoleObj> toForget = new LinkedList<ICFBamSchemaRoleObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaRole().deleteSchemaRoleByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeSchemaRoleByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteSchemaRoleByUDefIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 DefSchemaId,
		String Name )
	{
		if( indexByUDefIdx == null ) {
			indexByUDefIdx = new HashMap< ICFBamRoleDefByUDefIdxKey,
				ICFBamSchemaRoleObj >();
		}
		ICFBamRoleDefByUDefIdxKey key = schema.getCFBamBackingStore().getFactoryRoleDef().newByUDefIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalDefSchemaId( DefSchemaId );
		key.setRequiredName( Name );
		ICFBamSchemaRoleObj obj = null;
		if( indexByUDefIdx.containsKey( key ) ) {
			obj = indexByUDefIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaRole().deleteSchemaRoleByUDefIdx( null,
				ScopeId,
				DefSchemaId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaRole().deleteSchemaRoleByUDefIdx( null,
				ScopeId,
				DefSchemaId,
				Name );
		}
		deepDisposeSchemaRoleByUDefIdx( ScopeId,
				DefSchemaId,
				Name );
	}

	@Override
	public void deleteSchemaRoleBySchemaIdx( CFLibDbKeyHash256 SchemaDefId )
	{
		ICFBamSchemaRoleBySchemaIdxKey key = schema.getCFBamBackingStore().getFactorySchemaRole().newBySchemaIdxKey();
		key.setRequiredSchemaDefId( SchemaDefId );
		if( indexBySchemaIdx == null ) {
			indexBySchemaIdx = new HashMap< ICFBamSchemaRoleBySchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaRoleObj > >();
		}
		if( indexBySchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaRoleObj> dict = indexBySchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaRole().deleteSchemaRoleBySchemaIdx( null,
				SchemaDefId );
			Iterator<ICFBamSchemaRoleObj> iter = dict.values().iterator();
			ICFBamSchemaRoleObj obj;
			List<ICFBamSchemaRoleObj> toForget = new LinkedList<ICFBamSchemaRoleObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaRole().deleteSchemaRoleBySchemaIdx( null,
				SchemaDefId );
		}
		deepDisposeSchemaRoleBySchemaIdx( SchemaDefId );
	}
}