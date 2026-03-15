// Description: Java 25 Table Object implementation for PopTopDep.

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

public class CFBamPopTopDepTableObj
	implements ICFBamPopTopDepTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamPopTopDep.CLASS_CODE;
	protected static final int backingClassCode = ICFBamPopTopDep.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamPopTopDepObj> members;
	private Map<CFLibDbKeyHash256, ICFBamPopTopDepObj> allPopTopDep;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > > indexByTenantIdx;
	private Map< ICFBamPopDepByRelationIdxKey,
		Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > > indexByRelationIdx;
	private Map< ICFBamPopDepByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > > indexByDefSchemaIdx;
	private Map< ICFBamPopTopDepByContRelIdxKey,
		Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > > indexByContRelIdx;
	private Map< ICFBamPopTopDepByUNameIdxKey,
		ICFBamPopTopDepObj > indexByUNameIdx;
	public static String TABLE_NAME = "PopTopDep";
	public static String TABLE_DBNAME = "pop_topdep";

	public CFBamPopTopDepTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamPopTopDepObj>();
		allPopTopDep = null;
		indexByTenantIdx = null;
		indexByRelationIdx = null;
		indexByDefSchemaIdx = null;
		indexByContRelIdx = null;
		indexByUNameIdx = null;
	}

	public CFBamPopTopDepTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamPopTopDepObj>();
		allPopTopDep = null;
		indexByTenantIdx = null;
		indexByRelationIdx = null;
		indexByDefSchemaIdx = null;
		indexByContRelIdx = null;
		indexByUNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamPopTopDepTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamPopTopDepTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allPopTopDep = null;
		indexByTenantIdx = null;
		indexByRelationIdx = null;
		indexByDefSchemaIdx = null;
		indexByContRelIdx = null;
		indexByUNameIdx = null;
		List<ICFBamPopTopDepObj> toForget = new LinkedList<ICFBamPopTopDepObj>();
		ICFBamPopTopDepObj cur = null;
		Iterator<ICFBamPopTopDepObj> iter = members.values().iterator();
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
	 *	CFBamPopTopDepObj.
	 */
	@Override
	public ICFBamPopTopDepObj newInstance() {
		ICFBamPopTopDepObj inst = new CFBamPopTopDepObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamPopTopDepObj.
	 */
	@Override
	public ICFBamPopTopDepEditObj newEditInstance( ICFBamPopTopDepObj orig ) {
		ICFBamPopTopDepEditObj edit = new CFBamPopTopDepEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamPopTopDepObj realisePopTopDep( ICFBamPopTopDepObj Obj ) {
		ICFBamPopTopDepObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamPopTopDepObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamPopTopDepObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByRelationIdx != null ) {
				ICFBamPopDepByRelationIdxKey keyRelationIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
				keyRelationIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > mapRelationIdx = indexByRelationIdx.get( keyRelationIdx );
				if( mapRelationIdx != null ) {
					indexByRelationIdx.remove( keyRelationIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamPopDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByContRelIdx != null ) {
				ICFBamPopTopDepByContRelIdxKey keyContRelIdx =
					schema.getCFBamBackingStore().getFactoryPopTopDep().newByContRelIdxKey();
				keyContRelIdx.setRequiredContRelationId( keepObj.getRequiredContRelationId() );
				Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > mapContRelIdx = indexByContRelIdx.get( keyContRelIdx );
				if( mapContRelIdx != null ) {
					mapContRelIdx.remove( keepObj.getPKey() );
					if( mapContRelIdx.size() <= 0 ) {
						indexByContRelIdx.remove( keyContRelIdx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamPopTopDepByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryPopTopDep().newByUNameIdxKey();
				keyUNameIdx.setRequiredContRelationId( keepObj.getRequiredContRelationId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamPopTopDepObj)schema.getPopDepTableObj().realisePopDep( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRelationIdx != null ) {
				ICFBamPopDepByRelationIdxKey keyRelationIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
				keyRelationIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > mapRelationIdx = indexByRelationIdx.get( keyRelationIdx );
				if( mapRelationIdx != null ) {
					mapRelationIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamPopDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContRelIdx != null ) {
				ICFBamPopTopDepByContRelIdxKey keyContRelIdx =
					schema.getCFBamBackingStore().getFactoryPopTopDep().newByContRelIdxKey();
				keyContRelIdx.setRequiredContRelationId( keepObj.getRequiredContRelationId() );
				Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > mapContRelIdx = indexByContRelIdx.get( keyContRelIdx );
				if( mapContRelIdx != null ) {
					mapContRelIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamPopTopDepByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryPopTopDep().newByUNameIdxKey();
				keyUNameIdx.setRequiredContRelationId( keepObj.getRequiredContRelationId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( allPopTopDep != null ) {
				allPopTopDep.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamPopTopDepObj)schema.getPopDepTableObj().realisePopDep( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allPopTopDep != null ) {
				allPopTopDep.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRelationIdx != null ) {
				ICFBamPopDepByRelationIdxKey keyRelationIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
				keyRelationIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > mapRelationIdx = indexByRelationIdx.get( keyRelationIdx );
				if( mapRelationIdx != null ) {
					mapRelationIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamPopDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContRelIdx != null ) {
				ICFBamPopTopDepByContRelIdxKey keyContRelIdx =
					schema.getCFBamBackingStore().getFactoryPopTopDep().newByContRelIdxKey();
				keyContRelIdx.setRequiredContRelationId( keepObj.getRequiredContRelationId() );
				Map<CFLibDbKeyHash256, ICFBamPopTopDepObj > mapContRelIdx = indexByContRelIdx.get( keyContRelIdx );
				if( mapContRelIdx != null ) {
					mapContRelIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamPopTopDepByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryPopTopDep().newByUNameIdxKey();
				keyUNameIdx.setRequiredContRelationId( keepObj.getRequiredContRelationId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamPopTopDepObj createPopTopDep( ICFBamPopTopDepObj Obj ) {
		ICFBamPopTopDepObj obj = Obj;
		ICFBamPopTopDep rec = obj.getPopTopDepRec();
		schema.getCFBamBackingStore().getTablePopTopDep().createPopTopDep(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamPopTopDepObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamPopTopDepObj readPopTopDep( CFLibDbKeyHash256 pkey ) {
		return( readPopTopDep( pkey, false ) );
	}

	@Override
	public ICFBamPopTopDepObj readPopTopDep( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamPopTopDepObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamPopTopDep readRec = schema.getCFBamBackingStore().getTablePopTopDep().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamPopTopDepObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamPopTopDepObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamPopTopDepObj readCachedPopTopDep( CFLibDbKeyHash256 pkey ) {
		ICFBamPopTopDepObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposePopTopDep( ICFBamPopTopDepObj obj )
	{
		final String S_ProcName = "CFBamPopTopDepTableObj.reallyDeepDisposePopTopDep() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamPopTopDepObj existing = readCachedPopTopDep( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamPopTopDepByContRelIdxKey keyContRelIdx = schema.getCFBamBackingStore().getFactoryPopTopDep().newByContRelIdxKey();
		keyContRelIdx.setRequiredContRelationId( existing.getRequiredContRelationId() );

		ICFBamPopTopDepByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryPopTopDep().newByUNameIdxKey();
		keyUNameIdx.setRequiredContRelationId( existing.getRequiredContRelationId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );


		schema.getPopSubDep1TableObj().deepDisposePopSubDep1ByPopTopDepIdx( existing.getRequiredId() );

		if( indexByContRelIdx != null ) {
			if( indexByContRelIdx.containsKey( keyContRelIdx ) ) {
				indexByContRelIdx.get( keyContRelIdx ).remove( pkey );
				if( indexByContRelIdx.get( keyContRelIdx ).size() <= 0 ) {
					indexByContRelIdx.remove( keyContRelIdx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}


		schema.getPopDepTableObj().reallyDeepDisposePopDep( obj );
	}
	@Override
	public void deepDisposePopTopDep( CFLibDbKeyHash256 pkey ) {
		ICFBamPopTopDepObj obj = readCachedPopTopDep( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamPopTopDepObj lockPopTopDep( CFLibDbKeyHash256 pkey ) {
		ICFBamPopTopDepObj locked = null;
		ICFBamPopTopDep lockRec = schema.getCFBamBackingStore().getTablePopTopDep().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamPopTopDepObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamPopTopDepObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockPopTopDep", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamPopTopDepObj> readAllPopTopDep() {
		return( readAllPopTopDep( false ) );
	}

	@Override
	public List<ICFBamPopTopDepObj> readAllPopTopDep( boolean forceRead ) {
		final String S_ProcName = "readAllPopTopDep";
		if( ( allPopTopDep == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamPopTopDepObj> map = new HashMap<CFLibDbKeyHash256,ICFBamPopTopDepObj>();
			allPopTopDep = map;
			ICFBamPopTopDep[] recList = schema.getCFBamBackingStore().getTablePopTopDep().readAllDerived( null );
			ICFBamPopTopDep rec;
			ICFBamPopTopDepObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopTopDepObj realised = (ICFBamPopTopDepObj)obj.realise();
			}
		}
		int len = allPopTopDep.size();
		ICFBamPopTopDepObj arr[] = new ICFBamPopTopDepObj[len];
		Iterator<ICFBamPopTopDepObj> valIter = allPopTopDep.values().iterator();
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
		ArrayList<ICFBamPopTopDepObj> arrayList = new ArrayList<ICFBamPopTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopTopDepObj> cmp = new Comparator<ICFBamPopTopDepObj>() {
			@Override
			public int compare( ICFBamPopTopDepObj lhs, ICFBamPopTopDepObj rhs ) {
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
		List<ICFBamPopTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamPopTopDepObj> readCachedAllPopTopDep() {
		final String S_ProcName = "readCachedAllPopTopDep";
		ArrayList<ICFBamPopTopDepObj> arrayList = new ArrayList<ICFBamPopTopDepObj>();
		if( allPopTopDep != null ) {
			int len = allPopTopDep.size();
			ICFBamPopTopDepObj arr[] = new ICFBamPopTopDepObj[len];
			Iterator<ICFBamPopTopDepObj> valIter = allPopTopDep.values().iterator();
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
		Comparator<ICFBamPopTopDepObj> cmp = new Comparator<ICFBamPopTopDepObj>() {
			public int compare( ICFBamPopTopDepObj lhs, ICFBamPopTopDepObj rhs ) {
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
	public ICFBamPopTopDepObj readPopTopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readPopTopDepByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamPopTopDepObj readPopTopDepByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamPopTopDepObj obj = readPopTopDep( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamPopTopDepObj> readPopTopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readPopTopDepByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamPopTopDepObj> readPopTopDepByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readPopTopDepByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamPopTopDepObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamPopTopDepObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopTopDepObj realised = (ICFBamPopTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamPopTopDepObj arr[] = new ICFBamPopTopDepObj[len];
		Iterator<ICFBamPopTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamPopTopDepObj> arrayList = new ArrayList<ICFBamPopTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopTopDepObj> cmp = new Comparator<ICFBamPopTopDepObj>() {
			@Override
			public int compare( ICFBamPopTopDepObj lhs, ICFBamPopTopDepObj rhs ) {
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
		List<ICFBamPopTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamPopTopDepObj> readPopTopDepByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		return( readPopTopDepByRelationIdx( RelationId,
			false ) );
	}

	@Override
	public List<ICFBamPopTopDepObj> readPopTopDepByRelationIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readPopTopDepByRelationIdx";
		ICFBamPopDepByRelationIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
		key.setRequiredRelationId( RelationId );
		Map<CFLibDbKeyHash256, ICFBamPopTopDepObj> dict;
		if( indexByRelationIdx == null ) {
			indexByRelationIdx = new HashMap< ICFBamPopDepByRelationIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByRelationIdx.containsKey( key ) ) {
			dict = indexByRelationIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamPopTopDepObj>();
			ICFBamPopDepObj obj;
			ICFBamPopDep[] recList = schema.getCFBamBackingStore().getTablePopDep().readDerivedByRelationIdx( null,
				RelationId );
			ICFBamPopDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopTopDepObj realised = (ICFBamPopTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByRelationIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamPopTopDepObj arr[] = new ICFBamPopTopDepObj[len];
		Iterator<ICFBamPopTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamPopTopDepObj> arrayList = new ArrayList<ICFBamPopTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopTopDepObj> cmp = new Comparator<ICFBamPopTopDepObj>() {
			@Override
			public int compare( ICFBamPopTopDepObj lhs, ICFBamPopTopDepObj rhs ) {
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
		List<ICFBamPopTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamPopTopDepObj> readPopTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readPopTopDepByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamPopTopDepObj> readPopTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readPopTopDepByDefSchemaIdx";
		ICFBamPopDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamPopTopDepObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamPopDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamPopTopDepObj>();
			ICFBamPopDepObj obj;
			ICFBamPopDep[] recList = schema.getCFBamBackingStore().getTablePopDep().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamPopDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopTopDepObj realised = (ICFBamPopTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamPopTopDepObj arr[] = new ICFBamPopTopDepObj[len];
		Iterator<ICFBamPopTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamPopTopDepObj> arrayList = new ArrayList<ICFBamPopTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopTopDepObj> cmp = new Comparator<ICFBamPopTopDepObj>() {
			@Override
			public int compare( ICFBamPopTopDepObj lhs, ICFBamPopTopDepObj rhs ) {
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
		List<ICFBamPopTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamPopTopDepObj> readPopTopDepByContRelIdx( CFLibDbKeyHash256 ContRelationId )
	{
		return( readPopTopDepByContRelIdx( ContRelationId,
			false ) );
	}

	@Override
	public List<ICFBamPopTopDepObj> readPopTopDepByContRelIdx( CFLibDbKeyHash256 ContRelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readPopTopDepByContRelIdx";
		ICFBamPopTopDepByContRelIdxKey key = schema.getCFBamBackingStore().getFactoryPopTopDep().newByContRelIdxKey();
		key.setRequiredContRelationId( ContRelationId );
		Map<CFLibDbKeyHash256, ICFBamPopTopDepObj> dict;
		if( indexByContRelIdx == null ) {
			indexByContRelIdx = new HashMap< ICFBamPopTopDepByContRelIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByContRelIdx.containsKey( key ) ) {
			dict = indexByContRelIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamPopTopDepObj>();
			ICFBamPopTopDepObj obj;
			ICFBamPopTopDep[] recList = schema.getCFBamBackingStore().getTablePopTopDep().readDerivedByContRelIdx( null,
				ContRelationId );
			ICFBamPopTopDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopTopDepObj realised = (ICFBamPopTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContRelIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamPopTopDepObj arr[] = new ICFBamPopTopDepObj[len];
		Iterator<ICFBamPopTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamPopTopDepObj> arrayList = new ArrayList<ICFBamPopTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopTopDepObj> cmp = new Comparator<ICFBamPopTopDepObj>() {
			@Override
			public int compare( ICFBamPopTopDepObj lhs, ICFBamPopTopDepObj rhs ) {
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
		List<ICFBamPopTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamPopTopDepObj readPopTopDepByUNameIdx( CFLibDbKeyHash256 ContRelationId,
		String Name )
	{
		return( readPopTopDepByUNameIdx( ContRelationId,
			Name,
			false ) );
	}

	@Override
	public ICFBamPopTopDepObj readPopTopDepByUNameIdx( CFLibDbKeyHash256 ContRelationId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamPopTopDepByUNameIdxKey,
				ICFBamPopTopDepObj >();
		}
		ICFBamPopTopDepByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryPopTopDep().newByUNameIdxKey();
		key.setRequiredContRelationId( ContRelationId );
		key.setRequiredName( Name );
		ICFBamPopTopDepObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamPopTopDep rec = schema.getCFBamBackingStore().getTablePopTopDep().readDerivedByUNameIdx( null,
				ContRelationId,
				Name );
			if( rec != null ) {
				obj = (ICFBamPopTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamPopTopDepObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamPopTopDepObj readCachedPopTopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamPopTopDepObj obj = null;
		obj = readCachedPopTopDep( Id );
		return( obj );
	}

	@Override
	public List<ICFBamPopTopDepObj> readCachedPopTopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedPopTopDepByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamPopTopDepObj> arrayList = new ArrayList<ICFBamPopTopDepObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamPopTopDepObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamPopTopDepObj arr[] = new ICFBamPopTopDepObj[len];
				Iterator<ICFBamPopTopDepObj> valIter = dict.values().iterator();
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
			ICFBamPopTopDepObj obj;
			Iterator<ICFBamPopTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamPopTopDepObj> cmp = new Comparator<ICFBamPopTopDepObj>() {
			@Override
			public int compare( ICFBamPopTopDepObj lhs, ICFBamPopTopDepObj rhs ) {
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
	public List<ICFBamPopTopDepObj> readCachedPopTopDepByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "readCachedPopTopDepByRelationIdx";
		ICFBamPopDepByRelationIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
		key.setRequiredRelationId( RelationId );
		ArrayList<ICFBamPopTopDepObj> arrayList = new ArrayList<ICFBamPopTopDepObj>();
		if( indexByRelationIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamPopTopDepObj> dict;
			if( indexByRelationIdx.containsKey( key ) ) {
				dict = indexByRelationIdx.get( key );
				int len = dict.size();
				ICFBamPopTopDepObj arr[] = new ICFBamPopTopDepObj[len];
				Iterator<ICFBamPopTopDepObj> valIter = dict.values().iterator();
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
			ICFBamPopTopDepObj obj;
			Iterator<ICFBamPopTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamPopTopDepObj> cmp = new Comparator<ICFBamPopTopDepObj>() {
			@Override
			public int compare( ICFBamPopTopDepObj lhs, ICFBamPopTopDepObj rhs ) {
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
	public List<ICFBamPopTopDepObj> readCachedPopTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedPopTopDepByDefSchemaIdx";
		ICFBamPopDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamPopTopDepObj> arrayList = new ArrayList<ICFBamPopTopDepObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamPopTopDepObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamPopTopDepObj arr[] = new ICFBamPopTopDepObj[len];
				Iterator<ICFBamPopTopDepObj> valIter = dict.values().iterator();
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
			ICFBamPopTopDepObj obj;
			Iterator<ICFBamPopTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamPopTopDepObj> cmp = new Comparator<ICFBamPopTopDepObj>() {
			@Override
			public int compare( ICFBamPopTopDepObj lhs, ICFBamPopTopDepObj rhs ) {
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
	public List<ICFBamPopTopDepObj> readCachedPopTopDepByContRelIdx( CFLibDbKeyHash256 ContRelationId )
	{
		final String S_ProcName = "readCachedPopTopDepByContRelIdx";
		ICFBamPopTopDepByContRelIdxKey key = schema.getCFBamBackingStore().getFactoryPopTopDep().newByContRelIdxKey();
		key.setRequiredContRelationId( ContRelationId );
		ArrayList<ICFBamPopTopDepObj> arrayList = new ArrayList<ICFBamPopTopDepObj>();
		if( indexByContRelIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamPopTopDepObj> dict;
			if( indexByContRelIdx.containsKey( key ) ) {
				dict = indexByContRelIdx.get( key );
				int len = dict.size();
				ICFBamPopTopDepObj arr[] = new ICFBamPopTopDepObj[len];
				Iterator<ICFBamPopTopDepObj> valIter = dict.values().iterator();
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
			ICFBamPopTopDepObj obj;
			Iterator<ICFBamPopTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamPopTopDepObj> cmp = new Comparator<ICFBamPopTopDepObj>() {
			@Override
			public int compare( ICFBamPopTopDepObj lhs, ICFBamPopTopDepObj rhs ) {
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
	public ICFBamPopTopDepObj readCachedPopTopDepByUNameIdx( CFLibDbKeyHash256 ContRelationId,
		String Name )
	{
		ICFBamPopTopDepObj obj = null;
		ICFBamPopTopDepByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryPopTopDep().newByUNameIdxKey();
		key.setRequiredContRelationId( ContRelationId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamPopTopDepObj> valIter = members.values().iterator();
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
			Iterator<ICFBamPopTopDepObj> valIter = members.values().iterator();
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
	public void deepDisposePopTopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamPopTopDepObj obj = readCachedPopTopDepByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposePopTopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposePopTopDepByTenantIdx";
		ICFBamPopTopDepObj obj;
		List<ICFBamPopTopDepObj> arrayList = readCachedPopTopDepByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamPopTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposePopTopDepByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "deepDisposePopTopDepByRelationIdx";
		ICFBamPopTopDepObj obj;
		List<ICFBamPopTopDepObj> arrayList = readCachedPopTopDepByRelationIdx( RelationId );
		if( arrayList != null )  {
			Iterator<ICFBamPopTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposePopTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposePopTopDepByDefSchemaIdx";
		ICFBamPopTopDepObj obj;
		List<ICFBamPopTopDepObj> arrayList = readCachedPopTopDepByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamPopTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposePopTopDepByContRelIdx( CFLibDbKeyHash256 ContRelationId )
	{
		final String S_ProcName = "deepDisposePopTopDepByContRelIdx";
		ICFBamPopTopDepObj obj;
		List<ICFBamPopTopDepObj> arrayList = readCachedPopTopDepByContRelIdx( ContRelationId );
		if( arrayList != null )  {
			Iterator<ICFBamPopTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposePopTopDepByUNameIdx( CFLibDbKeyHash256 ContRelationId,
		String Name )
	{
		ICFBamPopTopDepObj obj = readCachedPopTopDepByUNameIdx( ContRelationId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamPopTopDepObj updatePopTopDep( ICFBamPopTopDepObj Obj ) {
		ICFBamPopTopDepObj obj = Obj;
		schema.getCFBamBackingStore().getTablePopTopDep().updatePopTopDep( null,
			Obj.getPopTopDepRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getPopTopDepTableObj().getClassCode() ) {
			obj = (ICFBamPopTopDepObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deletePopTopDep( ICFBamPopTopDepObj Obj ) {
		ICFBamPopTopDepObj obj = Obj;
		schema.getCFBamBackingStore().getTablePopTopDep().deletePopTopDep( null,
			obj.getPopTopDepRec() );
		Obj.forget();
	}

	@Override
	public void deletePopTopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamPopTopDepObj obj = readPopTopDep(Id);
		if( obj != null ) {
			ICFBamPopTopDepEditObj editObj = (ICFBamPopTopDepEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamPopTopDepEditObj)obj.beginEdit();
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
		deepDisposePopTopDepByIdIdx( Id );
	}

	@Override
	public void deletePopTopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopTopDepObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamPopTopDepObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTablePopTopDep().deletePopTopDepByTenantIdx( null,
				TenantId );
			Iterator<ICFBamPopTopDepObj> iter = dict.values().iterator();
			ICFBamPopTopDepObj obj;
			List<ICFBamPopTopDepObj> toForget = new LinkedList<ICFBamPopTopDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTablePopTopDep().deletePopTopDepByTenantIdx( null,
				TenantId );
		}
		deepDisposePopTopDepByTenantIdx( TenantId );
	}

	@Override
	public void deletePopTopDepByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		ICFBamPopDepByRelationIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
		key.setRequiredRelationId( RelationId );
		if( indexByRelationIdx == null ) {
			indexByRelationIdx = new HashMap< ICFBamPopDepByRelationIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopTopDepObj > >();
		}
		if( indexByRelationIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamPopTopDepObj> dict = indexByRelationIdx.get( key );
			schema.getCFBamBackingStore().getTablePopTopDep().deletePopTopDepByRelationIdx( null,
				RelationId );
			Iterator<ICFBamPopTopDepObj> iter = dict.values().iterator();
			ICFBamPopTopDepObj obj;
			List<ICFBamPopTopDepObj> toForget = new LinkedList<ICFBamPopTopDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByRelationIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTablePopTopDep().deletePopTopDepByRelationIdx( null,
				RelationId );
		}
		deepDisposePopTopDepByRelationIdx( RelationId );
	}

	@Override
	public void deletePopTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamPopDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamPopDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopTopDepObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamPopTopDepObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTablePopTopDep().deletePopTopDepByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamPopTopDepObj> iter = dict.values().iterator();
			ICFBamPopTopDepObj obj;
			List<ICFBamPopTopDepObj> toForget = new LinkedList<ICFBamPopTopDepObj>();
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
			schema.getCFBamBackingStore().getTablePopTopDep().deletePopTopDepByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposePopTopDepByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deletePopTopDepByContRelIdx( CFLibDbKeyHash256 ContRelationId )
	{
		ICFBamPopTopDepByContRelIdxKey key = schema.getCFBamBackingStore().getFactoryPopTopDep().newByContRelIdxKey();
		key.setRequiredContRelationId( ContRelationId );
		if( indexByContRelIdx == null ) {
			indexByContRelIdx = new HashMap< ICFBamPopTopDepByContRelIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopTopDepObj > >();
		}
		if( indexByContRelIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamPopTopDepObj> dict = indexByContRelIdx.get( key );
			schema.getCFBamBackingStore().getTablePopTopDep().deletePopTopDepByContRelIdx( null,
				ContRelationId );
			Iterator<ICFBamPopTopDepObj> iter = dict.values().iterator();
			ICFBamPopTopDepObj obj;
			List<ICFBamPopTopDepObj> toForget = new LinkedList<ICFBamPopTopDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByContRelIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTablePopTopDep().deletePopTopDepByContRelIdx( null,
				ContRelationId );
		}
		deepDisposePopTopDepByContRelIdx( ContRelationId );
	}

	@Override
	public void deletePopTopDepByUNameIdx( CFLibDbKeyHash256 ContRelationId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamPopTopDepByUNameIdxKey,
				ICFBamPopTopDepObj >();
		}
		ICFBamPopTopDepByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryPopTopDep().newByUNameIdxKey();
		key.setRequiredContRelationId( ContRelationId );
		key.setRequiredName( Name );
		ICFBamPopTopDepObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTablePopTopDep().deletePopTopDepByUNameIdx( null,
				ContRelationId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTablePopTopDep().deletePopTopDepByUNameIdx( null,
				ContRelationId,
				Name );
		}
		deepDisposePopTopDepByUNameIdx( ContRelationId,
				Name );
	}
}